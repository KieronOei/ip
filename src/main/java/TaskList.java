public class TaskList {
    private Task[] tasks;
    private int pointer;

    public TaskList() {
        // Following assumption from Level 2
        // Initialise an empty String array with 100 item capacity
        // Create a pointer that keeps track of the next empty slot
        tasks = new Task[100];
        pointer = 0;
    }

    public String add(Task task){
        if (pointer < tasks.length) {
            tasks[pointer] = task;
            pointer++;
            return "\n\tAdded: " + task;
        } else {
            return "\n\tTask list is full. Cannot add more tasks.";
        }
    }

    public String mark(boolean isMark, int taskNumber) {
        int index = taskNumber - 1;
        if (index < 0 || index >= pointer) {
            return "\n\tThere is no item at the specified address";
        } else if (isMark){
            tasks[index].mark();
            return "\n\tThat was quick! Task marked as done:\n\t" + tasks[index];
        } else {
            tasks[index].unmark();
            return "\n\tNo worries! Task marked as not done yet:\n\t" + tasks[index];
        }
    }

    public String display() {
        if (pointer == 0) {
            return "\n\tThere are no items in your list at the moment.";
        } else {
            String output = "";
            // For every item in the list, add it to output string
            for (int i = 0; i < pointer; i++) {
                output += String.format("\n\t%d. %s", i + 1, tasks[i]);
            }
            return output;
        }
    }
}
