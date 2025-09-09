import java.io.IOException;

/**
 * Deletes a task from the list based on its index.
 */
public class DeleteCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand for deleting a specific task.
     *
     * @param taskNumber The index of the task to delete (1-based)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete the specified task.
     * Updates the task list and persists the task deletion using FileHandler.
     *
     * @param taskList The list of tasks to modify
     * @param ui The UI for displaying output messages
     * @param fileHandler The handler responsible for saving task changes
     * @throws CortanaException if the task index is invalid
     * @throws IOException if an I/O error occurs during saving
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileHandler fileHandler) throws CortanaException, IOException {
        ui.showOutput(taskList.delete(taskNumber));
        fileHandler.saveDelete(taskList, taskNumber);
    }
}