import java.util.Scanner;

public class Cortana {
    public static void main(String[] args) {
        // ASCII art adapted from: https://patorjk.com/software/taag/
        String logo = "\t   ___           _                    \n"
                + "\t  / __\\___  _ __| |_ __ _ _ __   __ _ \n"
                + "\t / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
                + "\t/ /__| (_) | |  | || (_| | | | | (_| |\n"
                + "\t\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";
        String line = "\n\t___________________________________________________";

        String greeting = line + "\n\tWelcome back Master Chief! It's\n" + logo + "\n\tWhat can I do for you?" + line;

        String farewell = line + "\n\tLogging off, see you again soon!" + line;

        Scanner scanner = new Scanner(System.in);

        System.out.println(greeting);

        // Initialise
        TaskList taskList = new TaskList();

        while (true) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            String command = tokens[0];

            if (command.equalsIgnoreCase("bye")) {
                System.out.println(farewell);
                break;
            } else {
                switch (command.toLowerCase()) {
                    case "list":
                        System.out.println(line + taskList.display() + line);
                        break;
                    case "mark", "unmark":
                        if (tokens.length > 1) {
                            String argument = tokens[1];
                            if (command.equalsIgnoreCase("mark")) {
                                System.out.println(line + taskList.mark(true, Integer.parseInt(argument)) + line);
                            } else {
                                System.out.println(line + taskList.mark(false, Integer.parseInt(argument)) + line);
                            }
                        } else {
                            System.out.println(line + "\n\tChoose a task number to carry out the action" + line);
                        }
                        break;
                    default:
                        // Add item to task list
                        System.out.println(line + taskList.add(new Task(input)) + line);
                }
            }
        }
    }
}
