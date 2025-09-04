/**
 * Provides various static strings used by the Cortana chatbot,
 * including ASCII art logo, line separators, greetings, and farewell messages.
 */
public class CortanaString {

    /**
     * Returns the ASCII art logo of the Cortana chatbot.
     * The art is adapted from: https://patorjk.com/software/taag/
     *
     * @return a string containing the ASCII art logo
     */
    public static String logo() {
        return "\t   ___           _\n"
                + "\t  / __\\___  _ __| |_ __ _ _ __   __ _\n"
                + "\t / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
                + "\t/ /__| (_) | |  | || (_| | | | | (_| |\n"
                + "\t\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";
    }

    /**
     * Returns a line separator string used for formatting chatbot output.
     *
     * @return a string representing a horizontal line separator
     */
    public static String line() {
        return "\n\t________________________________________________________________________________";
    }

    /**
     * Returns the greeting message displayed to the user when the chatbot starts.
     * This includes the line separator, welcome message, and the ASCII art logo.
     *
     * @return the greeting string
     */
    public static String greeting() {
        return line() + "\n\tWelcome back Master Chief! It's\n" + logo() + "\n\tWhat can I do for you?" + line();
    }

    /**
     * Returns the farewell message displayed to the user when the chatbot exits.
     * This includes line separators and a logout message.
     *
     * @return the farewell string
     */
    public static String farewell() {
        return line() + "\n\tLogging off, see you again soon!" + line();
    }
}

