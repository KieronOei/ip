import java.util.Scanner;

/**
 * The Ui class handles all user interactions for the Cortana chatbot.
 * It manages input reading and formatted output display, including greetings and farewells.
 */
public class Ui {
    /**
     * Returns the ASCII art logo of the Cortana chatbot.
     * The art is adapted from: <a href="https://patorjk.com/software/taag/">TAAG</a>.
     */
    private static final String LOGO =
            "\t   ___           _\n"
            + "\t  / __\\___  _ __| |_ __ _ _ __   __ _\n"
            + "\t / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
            + "\t/ /__| (_) | |  | || (_| | | | | (_| |\n"
            + "\t\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";

    /**
     * A line separator string used for formatting chatbot output.
     */
    private static final String LINE =
            "\n\t________________________________________________________________________________\n";

    private final Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The user input string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays output text enclosed within line separators for better readability.
     *
     * @param output The message to display to the user.
     */
    public void showOutput(String output) {
        System.out.print(LINE + "\t" + output + LINE);
    }

    /**
     * Displays a greeting message along with the Cortana ASCII logo.
     */
    public void showGreeting() {
        showOutput("Welcome back Master Chief! It's\n" + LOGO + "\n\tWhat can I do for you?");
    }

    /**
     * Displays a farewell message signaling the end of the chatbot session.
     */
    public void showFarewell() {
        showOutput("Logging off, see you again soon!");
    }
}
