package helpers;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.IntPredicate;

/**
 * This class provides helper methods to check the elements of a class using reflection.
 */
public class ClassChecker {
    /**
     * This field is used in the hint() method to format the maximum line length.
     */
    private static final int MAX_LINE_LENGTH = 50;

    /**
     * Investigates a class if it has a private field of the given name and type.
     * It prints its results to System.out.
     *
     * @param _class The class to investigate.
     * @param fieldName The name of the field to investigate.
     * @param fieldType The type of the field to investigate.
     */
    public static boolean checkIfClassHasField(Class<?> _class, String fieldName, Class<?> fieldType, String hint) {
        System.out.printf("- Checking field '%s' of class '%s'.\n", fieldName, _class.getSimpleName());

        System.out.printf("  Does the class '%s' have a field called '%s'? ",_class.getName(), fieldName);
        Optional<Field> field = getFieldOfClass(_class, fieldName);
        if (field.isEmpty()) {
            System.out.println(no());
            hint(hint);

            return false;
        }
        else {
            System.out.println(yes());
            return (checkIfFieldHasType(field.get(), fieldType) &
                    checkIfMemberHasProperty(field.get(), Modifier::isPrivate, "private", "Fields should be private."));
        }
    }

    /**
     * Investigates a class if it has a constructor with the given parameter types.
     * It prints its results to System.out.
     *
     * @param _class The class to investigate.
     * @param parameterTypes One or more types of the required constructor parameters.
     */
    public static boolean checkIfClassHasConstructor(Class<?> _class, Set<Class<?>> parameterTypes) {
        System.out.println("- Checking constructor of class " + _class.getName());
        System.out.printf("  Does the class '%s' have a constructor with a parameter list of type(s) (%s)? ",
                _class.getName(), setToString(parameterTypes));

        // look for a constructor with the right parameter list
        // note that parameters can come in any order, and there may be additional parameters
        Constructor<?> constructor = null;
        for (Constructor<?> constructor1 : _class.getDeclaredConstructors()) {
            boolean doesConstructorHaveAllParameters = true;
            for (Class c : parameterTypes) {
                if (! Arrays.asList(constructor1.getParameterTypes()).contains(c)) {
                    doesConstructorHaveAllParameters = false;
                    break;
                }
            }
            if (doesConstructorHaveAllParameters) {
                constructor = constructor1;
                break;
            }
        }


        if (constructor == null) {
            System.out.println(no());
            hint (
                    String.format("The class '%s' needs a constructor with a parameter list of type(s) (%s). ",
                            _class.getName(), setToString(parameterTypes)) +
                            "Constructors initialize the fields of the object. Without a constructor, " +
                            "the fields may have the wrong values after initialization.");
        }
        else {
            System.out.println(yes());
        }

        if (constructor != null) {
            return checkIfMemberHasProperty(constructor,
                    Modifier::isPublic,
                    "public",
                    "Without a 'public' constructor, the class cannot be instantiated with 'new'.");
        }
        else {
            return false;
        }
    }

    private static String setToString(Set<Class<?>> parameterTypes) {
        StringBuilder sb = new StringBuilder();
        for (Class c : parameterTypes) {
            sb.append(c.getSimpleName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /**
     * Investigates a class if it has a public method of the given name and type.
     * It prints its results to System.out.
     *
     * @param _class The class to investigate.
     * @param methodName The name of the method to investigate.
     * @param returnType The return type of the method to investigate.
     * @param hint A hint to print to System.out if the method is not found.
     * @param parameterTypes Zero or more types of the required method parameters, in the exact order they appear in the method signature.
     */
    public static boolean checkIfClassHasMethod(Class _class, String methodName, Class returnType, String hint, Class... parameterTypes) {
        System.out.printf("- Checking method '%s' of class '%s'.\n",methodName, _class.getSimpleName());
        System.out.printf("  Does the class '%s' have a method called '%s'? ",_class.getName(), methodName);
        Optional<Method> method = getMethodOfClass(_class, methodName, parameterTypes);
        if (method.isEmpty()) {
            System.out.println(no());
            hint(hint);
            return false;
        } else {
            System.out.println(yes());
            return (checkIfMethodHasReturnType(method.get(), returnType) &
            checkIfMemberHasProperty(method.get(), Modifier::isPublic, "public"));
        }

    }


    private static boolean checkIfMethodHasReturnType(Method method, Class type) {
        System.out.printf("  Does the method '%s' have the return type '%s'? ",
                method.getName(), type.getSimpleName());
        if (method.getReturnType() == type) {
            System.out.println(yes());
            return true;
        } else {
            System.out.println(no());
            hint(String.format("Please add the return type '%s' to the method '%s'. " +
                    "The method has the return type %s, but it should have the return type %s.",
                    type.getName(), method.getName(),
                    method.getReturnType().getSimpleName(), type.getSimpleName()));
            return false;
        }
    }

    private static boolean checkIfMemberHasProperty(Member member, IntPredicate property, String propertyName) {
        return checkIfMemberHasProperty(member, property, propertyName, "");
    }

    private static boolean checkIfMemberHasProperty(Member member, IntPredicate property, String propertyName, String hint) {
        System.out.printf("  Does '%s' have the property '%s'? ", member.getName(), propertyName);
        if (property.test(member.getModifiers())) {
            System.out.println(yes());
            return true;
        }
        else {
            System.out.println(no());
            hint(String.format("Please add the '%s' modifier to '%s'." +
                    "The member %s currently has the visibility '%s'. " + hint,
                    propertyName, member.getName(),
                    member.getName(), Modifier.toString(member.getModifiers())));
            return false;
        }
    }

    private static Optional<Field> getFieldOfClass(Class _class, String fieldName) {
        Field field = null;
        try {
            field = _class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
        }
        return Optional.ofNullable(field);
    }

    private static Optional<Method> getMethodOfClass(Class _class, String methodName, Class... parameterTypes) {
        Method method = null;
        try {
            method = _class.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }
        return Optional.ofNullable(method);
    }


    private static boolean checkIfFieldHasType(Field field, Class type) {
        System.out.printf("  Does the field '%s' have the type '%s'? ", field.getName(), type.getSimpleName());
        if (field.getType() == type) {
            System.out.println(yes());
            return true;
        } else {
            System.out.println(no());
            hint(String.format("The field '%s' of the class '%s' must have type '%s'. ",
                    field.getName(), field.getDeclaringClass().getName(), type.getSimpleName()) +
                    String.format("Currently, it is declared as '%s %s %s.'",
                                Modifier.toString(field.getModifiers()),
                                    field.getType().getSimpleName(),
                                    field.getName()));
            return false;
        }
    }

    private static String classArrayToString(Class[] classes) {
        StringBuilder sb = new StringBuilder();
        for (Class clazz : classes) {
            sb.append(clazz.getSimpleName()).append(", ");
        }
        return sb.toString().substring(0, sb.length() - 2);
    }


    private static String yes () {
        return PrettyPrinter.green("Yes.");
    }

    private static String no () {
        return PrettyPrinter.red("No.");
    }


    private static void hint(String explanation) {
        String explanationString =
                "       ==---\n";

        for (String line : addLinebreaks(explanation, MAX_LINE_LENGTH, "       || ")) {
            explanationString += line + "\n";
        }
        explanationString += "       ==---\n";

        System.out.println(PrettyPrinter.green(explanationString));
    }

    public static List<String> addLinebreaks(String input, int maxLineLength, String prefix) {
        List<String> lines = new ArrayList<>();
        StringTokenizer tok = new StringTokenizer(input, " ");


        String currentLine = prefix;
        int lineLen = prefix.length();
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                lines.add(currentLine);
                currentLine = prefix;
                lineLen = prefix.length();
            }
            currentLine += word + " ";
            lineLen += word.length();
        }
        lines.add(currentLine);
        return lines;
    }

}
