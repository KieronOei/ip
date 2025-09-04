public class CommandHandler {

    public static void taskCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
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

                task = new Deadline(taskName, byAndString[1].trim());
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

                task = new Event(taskName, fromAndString[1].trim(), toAndString[1].trim());
                System.out.println(taskList.add(task));
                // save
                fileHandler.saveEvent(taskList, task.toString());
                break;
        }

    }

    public static void markCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.mark(Integer.parseInt(argument)));
            fileHandler.saveMarkValue(taskList, Integer.parseInt(argument), "1");
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g mark 1)");
        }
    }

    public static void unMarkCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.unmark(Integer.parseInt(argument)));
            fileHandler.saveMarkValue(taskList, Integer.parseInt(argument), "0");
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g mark 1)");
        }
    }

    public static void deleteCommand(TaskList taskList, String[] tokens, FileHandler fileHandler) throws CortanaException {
        if (tokens.length > 1) {
            String argument = tokens[1];
            System.out.println(taskList.delete(Integer.parseInt(argument)));
            fileHandler.saveDelete(taskList, Integer.parseInt(argument));
        } else {
            throw new CortanaException("Action failed. Choose a task number to carry out the action (e.g delete 3)");
        }
    }

    public static CommandType getCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }
}

