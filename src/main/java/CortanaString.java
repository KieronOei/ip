/**
 * Provides various static strings used by the Cortana chatbot,
 * including ASCII art logo, line separators, greetings, and farewell messages.
 */
public class CortanaString {

    /**
     * Returns the ASCII art logo of the Cortana chatbot.
     * The art is adapted from: https://patorjk.com/software/taag/
     */
    public static final String LOGO =
              "\t   ___           _\n"
            + "\t  / __\\___  _ __| |_ __ _ _ __   __ _\n"
            + "\t / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
            + "\t/ /__| (_) | |  | || (_| | | | | (_| |\n"
            + "\t\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";

    /**
     *  A line separator string used for formatting chatbot output.*
     */
    public static final String LINE =
                "\n\t________________________________________________________________________________";

    /**
     * The greeting message displayed to the user when the chatbot starts.
     * This includes the line separator, welcome message, and the ASCII art logo.
     */
    public static final String GREETING =
                LINE + "\n\tWelcome back Master Chief! It's\n" + LOGO + "\n\tWhat can I do for you?" + LINE;

    /**
     * The farewell message displayed to the user when the chatbot exits.
     * This includes line separators and a logout message.
     */
    public static final String FAREWELL = LINE + "\n\tLogging off, see you again soon!" + LINE;
}

