package cortana.command;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;
import java.io.IOException;

/** Interface representing a user command. */
public interface Command {
  /**
   * Executes the command using the specified task list, UI, and storage.
   *
   * @param tasks the task list to operate on
   * @param ui the UI helper for input/output
   * @param fileHandler the file handler for persistence
   * @throws CortanaException if command execution fails due to invalid input or logic error
   * @throws IOException if an I/O error occurs when saving/loading data
   */
  void execute(TaskList tasks, Ui ui, FileHandler fileHandler) throws CortanaException, IOException;

  /**
   * Returns true if this command signals the program should exit. Default implementation returns
   * false.
   *
   * @return whether the command is an exit command
   */
  default boolean isExit() {
    return false;
  }
}
