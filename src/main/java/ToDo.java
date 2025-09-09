/**
 * Represents a simple ToDo task with no date/time attached.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified name.
     *
     * @param name The name or description of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including the task type, completion status, and name.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
