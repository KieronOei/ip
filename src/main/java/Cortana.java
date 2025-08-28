import java.util.Scanner;

public class Cortana {
    public static void main(String[] args) {
        // ASCII art adapted from: https://patorjk.com/software/taag/
        String logo = "\t   ___           _\n"
                + "\t  / __\\___  _ __| |_ __ _ _ __   __ _\n"
                + "\t / /  / _ \\| '__| __/ _` | '_ \\ / _` |\n"
                + "\t/ /__| (_) | |  | || (_| | | | | (_| |\n"
                + "\t\\____/\\___/|_|   \\__\\__,_|_| |_|\\__,_|\n";
        String line = "\n\t________________________________________________________________________________";

        String greeting = line + "\n\tWelcome back Master Chief! It's\n" + logo + "\n\tWhat can I do for you?" + line;

        String farewell = line + "\n\tLogging off, see you again soon!" + line;

        Scanner scanner = new Scanner(System.in);

        System.out.println(greeting);

        // Initialise
        TaskList taskList = new TaskList();

        while (true) {
            String input = scanner.nextLine();
            try {
                // Split words up by white space
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
                                throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g mark 1)");
                            }
                            break;
                        case "todo", "deadline", "event":
                            // e.g tokens becomes ['deadline Read book', 'by Sunday'] or ['event Project meeting, 'from Mon 2pm', 'to 4pm']
                            tokens = input.split("/");
                            // Limit the split to 2 parts so you get e.g ['deadline', 'Read book']
                            String[] taskAndName = tokens[0].split(" ", 2);

                            // if input is "todo" or "todo "
                            if (taskAndName.length < 2 || taskAndName[1].trim().isEmpty()) {
                                throw new CortanaException("Action failed. Indicate the name of the task (e.g todo read book)");
                            }

                            String taskType = taskAndName[0];
                            String taskName = taskAndName[1];

                            // Use switch instead of if else statements to accommodate more task types in the future
                            switch (taskType.toLowerCase()) {
                                case "todo":
                                    System.out.println(line + taskList.add(new ToDo(taskName)) + line);
                                    break;
                                case "deadline":
                                    if (tokens.length < 2) {
                                        throw new CortanaException("Action failed. Missing '/by <description>' part");
                                    }
                                    // Use similar method to getting taskAndName to retrieve by
                                    // e.g ['by', 'Sunday'] or ['by', 'no idea :-p']
                                    String[] byAndString = tokens[1].split(" ", 2);

                                    // If input is "deadline /by"
                                    if (byAndString.length < 2 || byAndString[1].trim().isEmpty()) {
                                        throw new CortanaException("Action failed. Missing a non-empty description after '/by'");
                                    }

                                    String by = byAndString[1];
                                    System.out.println(line + taskList.add(new Deadline(taskName, by)) + line);
                                    break;
                                case "event":
                                    if (tokens.length < 3) {
                                        throw new CortanaException("Action failed. Missing '/from <description>' and/or '/to <description>' part(s)");
                                    }

                                    // Validate /from part
                                    String[] fromAndString = tokens[1].split(" ", 2);
                                    if (fromAndString.length < 2 || !fromAndString[0].equalsIgnoreCase("from") || fromAndString[1].trim().isEmpty()) {
                                        throw new CortanaException("Action failed. Missing a non-empty description after '/from'.");
                                    }
                                    String from = fromAndString[1].trim();

                                    // Validate /to part
                                    String[] toAndString = tokens[2].split(" ", 2);
                                    if (toAndString.length < 2 || !toAndString[0].equalsIgnoreCase("to") || toAndString[1].trim().isEmpty()) {
                                        throw new CortanaException("Action failed. Missing a non-empty description after '/to'.");
                                    }
                                    String to = toAndString[1].trim();

                                    System.out.println(line + taskList.add(new Event(taskName, from, to)) + line);
                                    break;
                            }
                            break;
                        default:
                            // Deal with unknown commands
                            throw new CortanaException("Sorry I don't understand what you want me to do, please try again");
                    }
                }
            } catch (CortanaException e) {
                System.out.println(line + "\n\t" + e.getMessage() + line);
            }
        }
    }
}
