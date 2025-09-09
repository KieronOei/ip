import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Cortana {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(CortanaString.GREETING);

        // Initialise
        FileHandler fileHandler = new FileHandler(Path.of("./data/cortana.txt"));
        TaskList taskList = new TaskList();

        // File Set Up
        try {
            fileHandler.ensureFileExists();
            fileHandler.checkAndPrepareFile();

            // Try to load data
            taskList = fileHandler.loadTasks();

        } catch (IOException | CortanaException e) {
            System.out.println("\t" + e.getMessage());
        }

        while (true) {
            String input = scanner.nextLine();
            try {
                // Split words up by white space
                String[] tokens = input.split(" ");
                CommandType command = CommandHandler.getCommandType(tokens[0]);

                // Handle command logic
                if (command.equals(CommandType.BYE)) {
                    System.out.println(CortanaString.FAREWELL);
                    break;
                } else {
                    switch (command) {
                    case LIST:
                            System.out.println(CortanaString.LINE + taskList + CortanaString.LINE);
                            break;
                    case MARK:
                        CommandHandler.handleMarkCommand(taskList, tokens, fileHandler);
                        break;
                    case UNMARK:
                        CommandHandler.handleUnMarkCommand(taskList, tokens, fileHandler);
                        break;
                    case TODO, DEADLINE, EVENT:
                        // e.g tokens becomes ['deadline Read book', 'by Sunday'] or ['event Project meeting, 'from Mon 2pm', 'to 4pm']
                        CommandHandler.handleTaskCommand(taskList, input.split("/"), fileHandler);
                        break;
                    case DELETE:
                        CommandHandler.handleDeleteCommand(taskList, tokens, fileHandler);
                        break;
                    default:
                        // Deal with unknown commands
                        throw new CortanaException("Sorry I don't understand what you want me to do, please try again");
                    }
                }
            } catch (CortanaException e) {
                System.out.println(CortanaString.LINE+ "\n\t" + e.getMessage() + CortanaString.LINE);
            }
        }
    }
}
