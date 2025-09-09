package cortana.core;

import cortana.command.Command;
import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The cortana.core.Cortana chatbot class responsible for initializing, running the main input loop,
 * and coordinating components.
 */
public class Cortana {

  private final FileHandler fileHandler;
  private final Ui ui;
  private TaskList tasks;

  /**
   * Initializes cortana.core.Cortana with the specified file path for storing tasks.
   *
   * @param filePath The path to the file used for task persistence.
   */
  public Cortana(String filePath) {
    ui = new Ui();
    fileHandler = new FileHandler(Path.of(filePath));
  }

  /**
   * Main method creates a cortana.core.Cortana instance with the task file path and runs it.
   *
   * @param args cortana.command.Command line arguments (unused).
   */
  public static void main(String[] args) {
    new Cortana("data/tasks.txt").run();
  }

  /**
   * Starts the chatbot, loading data, showing greetings, and running the command processing loop.
   */
  public void run() {
    // Initialise
    try {
      fileHandler.ensureFileExists();
      fileHandler.checkAndPrepareFile();
      tasks = fileHandler.loadTasks();
      ui.showOutput("Data has been loaded from: " + fileHandler.getFilePath());

    } catch (IOException | CortanaException e) {
      tasks = new TaskList();
      ui.showOutput(
          "Something went wrong, a new file has been created at: " + fileHandler.getFilePath());
    }

    boolean isExit = false;

    ui.showGreeting();

    // Loop for user commands until exit signal
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command command = Parser.parse(fullCommand);
        command.execute(tasks, ui, fileHandler);
        isExit = command.isExit();
      } catch (CortanaException e) {
        ui.showOutput(e.getMessage());
      } catch (IOException e) {
        ui.showOutput("File error occurred: " + e.getMessage());
      }
    }
  }
}
