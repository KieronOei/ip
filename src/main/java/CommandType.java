/**
 * Represents the types of commands that can be processed by Cortana.
 *
 * Includes task-related commands, task state changes, list commands, exit command,
 * and a special unknown type for unrecognized commands.
 */
public enum CommandType {
    /**
     * Command for creating a 'To Do' task.
     */
    TODO,

    /**
     * Command for creating a 'Deadline' task.
     */
    DEADLINE,

    /**
     * Command for creating an 'Event' task.
     */
    EVENT,

    /**
     * Command to mark a task as done.
     */
    MARK,

    /**
     * Command to unmark a task (mark as not done).
     */
    UNMARK,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to exit the chatbot application.
     */
    BYE,

    /**
     * Represents an unknown or unrecognized command.
     */
    UNKNOWN;

    /**
     * Converts a string command to corresponding CommandType enum value.
     * Returns UNKNOWN if no matching command is found.
     *
     * @param command String command (case-insensitive)
     * @return CommandType enum value
     */
    public static CommandType fromString(String command) {
        if (command == null) {
            return UNKNOWN;
        }
        try {
            return CommandType.valueOf(command.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
