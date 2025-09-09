package cortana.command;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

import java.io.IOException;

/**
 * Unmarks a task (marks it as not done) based on its index.
 */
public class UnMarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs an cortana.command.UnMarkCommand for unmarking a specific task.
     *
     * @param taskNumber The index of the task to unmark (1-based)
     */
    public UnMarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to unmark the specified task.
     * Updates the task list and persists the mark status using cortana.storage.FileHandler.
     *
     * @param taskList The list of tasks to modify
     * @param ui The UI for displaying output messages
     * @param fileHandler The handler responsible for saving task changes
     * @throws CortanaException if the task index is invalid
     * @throws IOException if an I/O error occurs during saving
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileHandler fileHandler) throws CortanaException, IOException {
        ui.showOutput(taskList.unmark(taskNumber));
        fileHandler.saveMarkValue(taskList, taskNumber, "0");
    }
}