package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

/**
 * Lists all tasks currently stored in the task list. Displays the tasks to the user via the UI.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command which outputs the current task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, FileHandler fileHandler) {
        return taskList.toString();
    }
}
