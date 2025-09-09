package cortana.task;

import cortana.exception.CortanaException;

import java.util.ArrayList;

/**
 * Maintains a list of tasks with operations to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    /**
     * Internal list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a cortana.task.TaskList with an initial capacity of 100.
     */
    public TaskList() {
        // Similar to Level 2, assume capacity of 100 items
        tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @return Confirmation message including the added task and total task count.
     */
    public String add(Task task) {
        tasks.add(task);
        return "Added:\n\t\t" + task + "\n\tNow you have " + (tasks.size()) + " task(s) in the list";
    }

    /**
     * Deletes a task by its task number.
     *
     * @param taskNumber The 1-based index of the task to delete.
     * @return Confirmation message including the deleted task and updated count.
     * @throws CortanaException if the task number is invalid.
     */
    public String delete(int taskNumber) throws CortanaException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new CortanaException("Action failed. Choose a valid task number");
        } else {
            Task task = tasks.remove(index);
            return "Deleted:\n\t\t" + task + "\n\tNow you have " + (tasks.size()) + " task(s) in the list";
        }
    }

    /**
     * Marks a task as done by its task number.
     *
     * @param taskNumber The 1-based index of the task to mark.
     * @return Confirmation message including the marked task.
     * @throws CortanaException if the task number is invalid.
     */
    public String mark(int taskNumber) throws CortanaException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new CortanaException("Action failed. Choose a valid task number");
        } else {
            tasks.get(index).mark();
            return "That was quick! cortana.task.Task marked as done:\n\t" + tasks.get(index);
        }
    }

    /**
     * Unmarks a task (sets as not done) by its task number.
     *
     * @param taskNumber The 1-based index of the task to unmark.
     * @return Confirmation message including the unmarked task.
     * @throws CortanaException if the task number is invalid.
     */
    public String unmark(int taskNumber) throws CortanaException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new CortanaException("Action failed. Choose a valid task number");
        } else {
            tasks.get(index).unmark();
            return "No worries! cortana.task.Task marked as not done yet:\n\t" + tasks.get(index);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a formatted string listing all tasks or an empty message if the list is empty.
     *
     * @return String representation of all tasks or a no-items message.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "\n\tThere are no items in your list at the moment.";
        } else {
            String output = "\n\tHere are the tasks in your list:";
            // For every item in the list, add it to output string
            for (int i = 0; i < tasks.size(); i++) {
                output += String.format("\n\t%d. %s", i + 1, tasks.get(i));
            }
            return output;
        }
    }
}
