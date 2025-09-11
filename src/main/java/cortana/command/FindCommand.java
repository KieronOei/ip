package cortana.command;

import java.io.IOException;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Finds tasks containing the specified keyword in their description
 * and displays the matching tasks to the user.
 */
public class FindCommand implements Command {

    /**
     * The keyword to search for within task descriptions.
     */
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
     */
    @Override
    public String execute(TaskList tasks, FileHandler fileHandler) throws CortanaException, IOException {
        return "Here are the matching tasks in your list:\n\t" + tasks.find(keyword).toString();
    }
}
