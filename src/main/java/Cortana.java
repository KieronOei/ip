public class Cortana {
    public static void main(String[] args) {
        // ASCII art adapted from: https://patorjk.com/software/taag/
        String logo = "   ___           _                    \n"
                + "  / __\\___  _ __| |_ __ _ _ __   __ _ \n"
                + " / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
                + "/ /__| (_) | |  | || (_| | | | | (_| |\n"
                + "\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";
        String line = "\n___________________________________________________";

        String greeting = "\nWelcome back Master Chief! It's\n" + logo + "\nWhat can I do for you?";

        String farewell = "\nLogging off, see you again soon!";

        System.out.println(line + greeting + line + farewell + line);
    }
}
