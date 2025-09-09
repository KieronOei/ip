package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

/** Lists all tasks currently stored in the task list. Displays the tasks to the user via the UI. */
public class ListCommand implements Command {

  /**
   * Executes the list command which outputs the current task list.
   *
   * @param taskList The current task list to display.
   * @param ui The UI used to show output messages.
   * @param fileHandler The file handler (not used in this command).
   */
  @Override
  public void execute(TaskList taskList, Ui ui, FileHandler fileHandler) {
    ui.showOutput(taskList.toString());
  }
}
