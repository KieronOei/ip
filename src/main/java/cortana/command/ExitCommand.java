package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

/**
 * Represents the exit command which ends the chatbot session.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, displaying a farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, FileHandler fileHandler) {
        return "Exit";
    }

    /**
     * Indicates that this command signals program termination.
     *
     * @return true since this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
