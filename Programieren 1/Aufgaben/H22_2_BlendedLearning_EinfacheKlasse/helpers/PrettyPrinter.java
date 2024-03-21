package helpers;
/**
 * This class provides methods to color strings.
 */
public class PrettyPrinter {
    public static boolean ANSI_SUPPORT = true;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Returns a string in a given color using ANSI Codes.
     * @param s The string to color.
     * @param color The desired color as ANSI code.
     * @return The colored string.
     */
    public static String color (String s, String color) {
        if (ANSI_SUPPORT) {
            return color + s + ANSI_RESET;
        } else {
            return s;
        }
    }

    /**
     * Returns a string in red using ANSI Codes.
     * @param s The string to color.
     * @return The colored string.
     */
    public static String red (String s) {
        return color(s, ANSI_RED);
    }

    /**
     * Returns a string in green using ANSI Codes.
     * @param s The string to color.
     * @return The colored string.
     */
    public static String green (String s) {
        return color (s, ANSI_GREEN);
    }

}
