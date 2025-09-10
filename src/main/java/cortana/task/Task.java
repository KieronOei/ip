package cortana.task;

import cortana.exception.CortanaException;

/**
 * Abstract base class representing a cortana.task.Task. Contains common fields and behavior for all
 * task types.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a cortana.task.Task with the specified name.
     *
     * @param name the name/description of the task
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Marks this task as done.
     *
     * @throws CortanaException if the task is already marked as done
     */
    public void mark() throws CortanaException {
        if (isDone) {
            throw new CortanaException("Action failed. cortana.task.Task is already marked as done");
        }
        isDone = true;
    }

    /**
     * Marks this task as not done.
     *
     * @throws CortanaException if the task is already marked as not done
     */
    public void unmark() throws CortanaException {
        if (!isDone) {
            throw new CortanaException("Action failed. cortana.task.Task is already marked as undone");
        }
        isDone = false;
    }

    /**
     * Returns string representation of the task. Subclasses should override for specific formatting.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }
}
