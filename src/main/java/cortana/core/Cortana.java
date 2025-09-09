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
   * Initializes a new instance of the Cortana chatbot with
   * the specified UI and file handler components.
   *
   * @param ui The UI component used for user interaction.
   * @param fileHandler The file handler used for task persistence.
   */
  public Cortana(Ui ui, FileHandler fileHandler) {
    this.ui = ui;
    this.fileHandler = fileHandler;
  }


  /**
   * Main method creates a cortana.core.Cortana instance with the task file path and runs it.
   *
   * @param args cortana.command.Command line arguments (unused).
   */
  public static void main(String[] args) {
    new Cortana(new Ui(), new FileHandler(Path.of("data/tasks.txt"))).run();
  }

  /**
   * Initializes the chatbot by ensuring the task file exists, preparing it,
   * loading tasks, and displaying relevant output messages.
   * <p>
   * If loading tasks fails due to IO or Cortana exceptions, a new task list is created,
   * and an appropriate message is displayed.
   * <p>
   * Finally, it shows a greeting message to the user.
   */
  public void initialize() {
    try {
      fileHandler.ensureFileExists();
      fileHandler.checkAndPrepareFile();
      tasks = fileHandler.loadTasks();
      ui.showOutput("Data has been loaded from: " + fileHandler.getFilePath());
    } catch (IOException | CortanaException e) {
      tasks = new TaskList();
      ui.showOutput("Something went wrong, a new file has been created at: " + fileHandler.getFilePath());
    }
    ui.showGreeting();
  }

  /**
   * Starts the chatbot, loading data, showing greetings, and running the command processing loop.
   */
  public void run() {
    initialize();

    boolean isExit = false;
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
