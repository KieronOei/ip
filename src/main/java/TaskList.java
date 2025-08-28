import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        // Similar to Level 2, assume capacity of 100 items
        tasks = new ArrayList<>(100);
    }

    public String add(Task task) {
        tasks.add(task);
        return "\n\tAdded:\n\t\t" + task + "\n\tNow you have " + (tasks.size()) + " task(s) in the list";
    }

    public String delete(int taskNumber) throws CortanaException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new CortanaException("Action failed. Choose a valid task number");
        } else {
            Task task = tasks.remove(index);
            return "\n\tDeleted:\n\t\t" + task + "\n\tNow you have " + (tasks.size()) + " task(s) in the list";
        }
    }

    public String mark(boolean isMark, int taskNumber) throws CortanaException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new CortanaException("Action failed. Choose a valid task number");
        } else if (isMark){
            tasks.get(index).mark();
            return "\n\tThat was quick! Task marked as done:\n\t" + tasks.get(index);
        } else {
            tasks.get(index).unmark();
            return "\n\tNo worries! Task marked as not done yet:\n\t" + tasks.get(index);
        }
    }

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
