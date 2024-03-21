import helpers.ClassChecker;
import helpers.PrettyPrinter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * This class checks if the Employee class with implemented correctly.
 */
public class EmployeeChecker {
    private static final Class classToCheck = Employee.class;
    private static final String nameField = "lastName";
    private static final String nameGetter = "getLastName";
    private static final Class nameType = String.class;
    private static final String salaryField = "annualSalary";
    private static final String salaryGetter = "getAnnualSalary";
    private static final String salarySetter = "setAnnualSalary";
    private static final Class salaryType = double.class;

    /**
     * Invokes structural tests and behavior tests on the class Employee.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        if (!checkEmployeeStructure()) {
            System.out.println(PrettyPrinter.red(
                    "Employee class has structural problems, see above. " +
                            "Therefore, skipping unit tests."));
        } else {
            System.out.println(PrettyPrinter.green("The structure of the Employee class is OK."));

            if (checkEmployeeBehavior()) {
                success("**************************\n* All unit tests passed! *\n**************************");
            } else {
                error("**************************\n* Some unit tests failed! *\n**************************");
            }
        }
    }

    /**
     * Checks if the Employee class has the correct structure (fields, constructors, methods).
     * <p>
     * Hint: By using && to logically connect the checks below, the evaluation stops after encountering a false check.
     * Thus, at most one problem is shown. If all occurrences of '&&' are replaced with '&', all checks are evaluated
     * and thus, all problems are shown at once.
     *
     * @return true if all checks were successful, false otherwise.
     */
    private static boolean checkEmployeeStructure() {
        System.out.println("Investigating the structure of the Employee class...");

        // first, check if class is empty. If it is, we skip the rest of the checks.
        if (hasMissingFieldsOrMethods()) return false;

        return (
                ClassChecker.checkIfClassHasField(
                        classToCheck,
                        nameField,
                        nameType,
                        "The class 'Employee' must have a field called 'lastName' of type 'String' such that we can " +
                                "store the name of each object/instance of this class.") &&
                        ClassChecker.checkIfClassHasField(
                                classToCheck,
                                salaryField,
                                salaryType,
                                "The class 'Employee' must have a field called 'annualSalary' of type 'double' such that we can " +
                                        "store the salary of each object/instance of this class.") &&
                        ClassChecker.checkIfClassHasConstructor(
                                classToCheck,
                                Set.of(nameType, salaryType)) &&
                        ClassChecker.checkIfClassHasMethod(
                                classToCheck,
                                nameGetter,
                                nameType,
                                "Without a get() method, the 'lastName' field is not accessible.") &&
                        ClassChecker.checkIfClassHasMethod(
                                classToCheck,
                                salaryGetter,
                                salaryType,
                                "Without a get() method, the 'getAnnualSalary' field is not accessible.") &&
                        ClassChecker.checkIfClassHasMethod(
                                classToCheck,
                                salarySetter,
                                void.class,
                                "The salary must be changeable. Therefore, a method 'setAnnualSalary' must be defined.",
                                salaryType)
        );
    }

    private static boolean hasMissingFieldsOrMethods() {
        if (Employee.class.getDeclaredFields().length == 0 && Employee.class.getDeclaredMethods().length == 0) {
            error("The Employee class does not have fields and methods. Please follow the instructions (\"TODOs\" in Employee.java) and fill it with the required fields, constructors and methods.");
            return true;
        }
        if (Employee.class.getDeclaredFields().length == 0) {
            error("The Employee class has methods, but not yet any fields. Please follow the instructions (\"TODOs\" in Employee.java) and fill it with the required fields.");
            return true;
        }
        if (Employee.class.getDeclaredMethods().length == 0) {
            error("The Employee class has fields, but not yet any methods. Please follow the instructions (\"TODOs\" in Employee.java) and fill it with the required constructors and methods.");
            return true;
        }
        return false;
    }

    /**
     * Checks if the Employee class has the correct behavior (correctly sets the name and salary, and returns the correct name and salary).
     *
     * @return true if all checks were successful, false otherwise.
     */
    private static boolean checkEmployeeBehavior() {
        /*
         * Since the initial Employee class is empty, typical unit test code (e.g., "new Employee(...)") would not
         * compile. So we are using reflection to check if Employee has a constructor, and later we use reflection
         * to get a reference to the methods of the class.
         */
        System.out.println("\nStarting unit tests...");
        boolean testsPassed = true;
        try {
            double exampleSalary = 100_000.0;
            Employee employee1 = Employee.class.getDeclaredConstructor(String.class, double.class).newInstance("Smith", exampleSalary);
            testsPassed &= testEmployeeName(employee1, "Smith");
            testsPassed &= testEmployeeSalary(employee1, exampleSalary);
            testsPassed &= testEmployeeSetSalary(employee1);
        } catch (Exception e) {
            error("This should not have happened. There was an exception invoking the methods of Employee through reflection, but we tested earlier that these methods exist and are accessible.");
            testsPassed = false;
        }
        return testsPassed;
    }

    private static boolean testEmployeeSetSalary(Employee employee1) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method setSalary = Employee.class.getMethod(salarySetter, salaryType);
        Method getSalary = Employee.class.getMethod(salaryGetter);

        // check if a valid values is accepted
        setSalary.invoke(employee1, 20.0);
        if (!getSalary.invoke(employee1).equals(20.0)) {
            error("  The setSalary method did not set the salary correctly.");
            return false;
        }

        // check that a negative values is NOT accepted
        setSalary.invoke(employee1, -1.0);
        if (!getSalary.invoke(employee1).equals(20.0)) {
            error("  The setSalary method must not accept negative values.");
            return false;
        }

        // check that a value that is outside of the valid range is NOT accepted
        setSalary.invoke(employee1, 2_000_000.0);
        if (!getSalary.invoke(employee1).equals(20.0)) {
            error("  The setSalary method must not accept values above 1'000'000.0.");
            return false;
        }

        success("  The setSalary method works correctly.");
        return true;
    }


    private static boolean testEmployeeName(Employee employee1, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getNameMethod = Employee.class.getMethod(nameGetter);

        if (!getNameMethod.invoke(employee1).equals(name)) {
            error("  The name of the employee is not correct.");
            error("  Please check if the constructor of class 'Employee' correctly sets the 'name' field, and the 'name' field is returned by the method 'getName()'.");
            return false;
        } else {
            success("  The name of the employee is correct.");
            return true;
        }
    }

    private static boolean testEmployeeSalary(Employee employee1, double salary) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getSalaryMethod = Employee.class.getMethod(salaryGetter);

        if (!getSalaryMethod.invoke(employee1).equals(salary)) {
            error("  The salary of the employee is not correct.");
            error("  Please check if the constructor of class 'Employee' correctly sets the 'salary' field, and the 'salary' field is returned by the method 'getSalary()'.");
            return false;
        } else {
            success("  The salary of the employee is correct.");
            return true;
        }
    }

    private static void error(String s) {
        System.out.println(PrettyPrinter.red(s));
    }

    private static void success(String s) {
        System.out.println(PrettyPrinter.green(s));
    }
}
