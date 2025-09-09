/**
 * Represents the exit command which ends the chatbot session.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, displaying a farewell message.
     *
     * @param tasks       The task list (not used in this command)
     * @param ui          The UI for displaying output messages
     * @param fileHandler The handler responsible for saving task changes (not used here)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, FileHandler fileHandler) {
        ui.showFarewell();
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