package cortana.command;

import java.io.IOException;
import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

/**
 * Finds tasks containing the specified keyword in their description
 * and displays the matching tasks to the user.
 */
public class FindCommand implements Command {

    /** The keyword to search for within task descriptions. */
    private String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the task list for tasks
     * containing the keyword and displaying the results in the UI.
     *
     * @param tasks the list of tasks to search within
     * @param ui the UI instance to show output messages
     * @param fileHandler the file handler (not used in this command)
     * @throws CortanaException if an error occurs related to Cortana operations
     * @throws IOException if an I/O error occurs during command execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, FileHandler fileHandler) throws CortanaException, IOException {
        ui.showOutput("Here are the matching tasks in your list:\n\t" + tasks.find(keyword).toString());
    }
}
