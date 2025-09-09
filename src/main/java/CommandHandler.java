import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles commands related to task management, including adding,
 * marking, unmarking, and deleting tasks. Supports parsing of deadlines
 * and events with multiple date formats.
 */
public class CommandHandler {

    /**
     * Processes a task command such as todo, deadline, or event.
     * Parses the command tokens, validates input, creates the corresponding Task,
     * adds it to the TaskList, and saves the updated list using FileHandler.
     *
     * @param taskList The list of tasks currently managed
     * @param tokens The command tokens from the user input
     * @param fileHandler The handler responsible for saving tasks to storage
     * @throws CortanaException if input format is invalid or required parameters are missing
     */
    public static void handleTaskCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        // Declare Task object for assignment later
        Task task;

        // Limit the split to 2 parts so you get e.g ['deadline', 'Read book']
        String[] taskAndName = tokens[0].split(" ", 2);

        if (taskAndName.length < 2 || taskAndName[1].trim().isEmpty()) {
            throw new CortanaException("Action failed. Indicate the name of the task (e.g todo read book)");
        }

        String commandStr = taskAndName[0];
        CommandType commandType = getCommandType(commandStr);
        String taskName = taskAndName[1].trim();

        // Use switch instead of if else statements to accommodate more task types in the future
        switch (commandType) {
        case TODO:
            task = new ToDo(taskName);
            System.out.println(taskList.add(task));
            // save
            fileHandler.saveToDo(taskList, task.toString());
            break;

        case DEADLINE:
            if (tokens.length < 2) {
                throw new CortanaException("Action failed. Missing '/by'");
            }
            // Use similar method to getting taskAndName to retrieve by
            // e.g ['by', 'Sunday'] or ['by', 'no idea :-p']
            String[] byAndString = tokens[1].split(" ", 2);

            // If input is "deadline /by"
            if (byAndString.length < 2 || byAndString[1].trim().isEmpty()) {
                throw new CortanaException("Action failed. Missing a non-empty description after '/by'");
            }

            // Assume byAndString[1] is "2/12/2025 1800" or "2 OCT 25 1800"
            String byRaw = byAndString[1].trim();
            LocalDateTime deadlineDateTime;

            try {
                // Accept multiple formats
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d M yy HHmm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yy HHmm");
                try {
                    deadlineDateTime = LocalDateTime.parse(byRaw, formatter1);
                } catch (DateTimeParseException e1) {
                    deadlineDateTime = LocalDateTime.parse(byRaw, formatter2);
                }
            }  catch (DateTimeParseException e) {
                throw new CortanaException("Invalid date format for deadline. Please use d M yyyy HHmm or d MMM yy HHmm");
            }

            task = new Deadline(taskName, deadlineDateTime);
            System.out.println(taskList.add(task));
            // save
            fileHandler.saveDeadline(taskList, task.toString());
            break;

        case EVENT:
            if (tokens.length < 3) {
                throw new CortanaException("Action failed. Missing '/from ' and/or '/to ' parts");
            }

            // Validate /from part
            String[] fromAndString = tokens[1].split(" ", 2);
            if (fromAndString.length < 2 || !fromAndString[0].equalsIgnoreCase("from") || fromAndString[1].trim().isEmpty()) {
                throw new CortanaException("Action failed. Missing a non-empty description after '/from'");
            }

            // Validate /to part
            String[] toAndString = tokens[2].split(" ", 2);
            if (toAndString.length < 2 || !toAndString[0].equalsIgnoreCase("to") || toAndString[1].trim().isEmpty()) {
                throw new CortanaException("Action failed. Missing a non-empty description after '/to'");
            }

            // Assume fromAndString[1] & toAndString[1] is "2/12/2025 1800" or "2 OCT 25 1800"
            String fromRaw = fromAndString[1].trim();
            String toRaw = toAndString[1].trim();
            LocalDateTime toDateTime;
            LocalDateTime fromDateTime;

            try {
                // Accept multiple formats
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d M yy HHmm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yy HHmm");
                try {
                    toDateTime = LocalDateTime.parse(toRaw, formatter1);
                    fromDateTime = LocalDateTime.parse(fromRaw, formatter1);
                } catch (DateTimeParseException e1) {
                    toDateTime = LocalDateTime.parse(toRaw, formatter2);
                    fromDateTime = LocalDateTime.parse(fromRaw, formatter2);
                }
            }  catch (DateTimeParseException e) {
                throw new CortanaException("Invalid date format for event. Please use d M yyyy HHmm or d MMM yy HHmm");
            }

            task = new Event(taskName, fromDateTime, toDateTime);
            System.out.println(taskList.add(task));
            // save
            fileHandler.saveEvent(taskList, task.toString());
            break;
        }

    }

    /**
     * Marks a task as done based on its index provided in the tokens.
     * Updates the TaskList and saves the mark value using FileHandler.
     *
     * @param taskList The list of tasks to modify
     * @param tokens The command tokens containing the index to mark
     * @param fileHandler The handler responsible for saving mark value changes
     * @throws CortanaException if no valid task index is provided
     */
    public static void handleMarkCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.mark(Integer.parseInt(argument)));
            fileHandler.saveMarkValue(taskList, Integer.parseInt(argument), "1");
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g mark 1)");
        }
    }

    /**
     * Unmarks a task (sets as not done) based on its index from the tokens.
     * Updates the TaskList and saves the mark value using FileHandler.
     *
     * @param taskList The list of tasks to modify
     * @param tokens The command tokens containing the index to unmark
     * @param fileHandler The handler responsible for saving mark value changes
     * @throws CortanaException if no valid task index is provided
     */
    public static void handleUnMarkCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.unmark(Integer.parseInt(argument)));
            fileHandler.saveMarkValue(taskList, Integer.parseInt(argument), "0");
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g mark 1)");
        }
    }

    /**
     * Deletes a task from the TaskList based on the index provided in tokens.
     * Saves the updated task list using FileHandler.
     *
     * @param taskList The list of tasks to modify
     * @param tokens The command tokens containing the index of the task to delete
     * @param fileHandler The handler responsible for saving the deletion
     * @throws CortanaException if no valid task index is provided
     */
    public static void handleDeleteCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.delete(Integer.parseInt(argument)));
            fileHandler.saveDelete(taskList, Integer.parseInt(argument));
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g delete 3)");
        }
    }

    /**
     * Converts a command string to a CommandType enum value.
     * Returns UNKNOWN if the command string does not match any CommandType.
     *
     * @param command The command string input by the user
     * @return Corresponding CommandType enum value or UNKNOWN if unrecognized
     */
    public static CommandType getCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }
}

