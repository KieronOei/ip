/**
 * Represents the types of commands that can be processed by Cortana.
 *
 * Includes task-related commands, task state changes, list commands, exit command,
 * and a special unknown type for unrecognized commands.
 */
public enum CommandType {
    /** Command for creating a 'To Do' task. */
    TODO,

    /** Command for creating a 'Deadline' task. */
    DEADLINE,

    /** Command for creating an 'Event' task. */
    EVENT,

    /** Command to mark a task as done. */
    MARK,

    /** Command to unmark a task (mark as not done). */
    UNMARK,

    /** Command to delete a task. */
    DELETE,

    /** Command to list all tasks. */
    LIST,

    /** Command to exit the chatbot application. */
    BYE,

    /** Represents an unknown or unrecognized command. */
    UNKNOWN
}
