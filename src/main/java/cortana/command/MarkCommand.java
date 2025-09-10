package cortana.command;

import java.io.IOException;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

/**
 * Marks a task as done based on its index.
 */
public class MarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a cortana.command.MarkCommand for marking a specific task.
     *
     * @param taskNumber The index of the task to mark (1-based)
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark the specified task as done. Updates the task list and persists the
     * mark status using cortana.storage.FileHandler.
     *
     * @param taskList    The list of tasks to modify
     * @param ui          The UI for displaying output messages
     * @param fileHandler The handler responsible for saving task changes
     * @throws CortanaException if the task index is invalid
     * @throws IOException      if an I/O error occurs during saving
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileHandler fileHandler)
            throws CortanaException, IOException {
        ui.showOutput(taskList.mark(taskNumber));
        fileHandler.saveMarkValue(taskList, taskNumber, "1");
    }
}
