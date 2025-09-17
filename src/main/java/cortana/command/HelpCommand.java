package cortana.command;

/**
 * Displays a list of available commands and their descriptions to assist the user.
 */
public class HelpCommand implements Command {
    @Override
    public String execute(cortana.task.TaskList taskList, cortana.storage.FileHandler fileHandler) {
        return "Here are the available commands:\n"
                + "-> todo <task_name> - Adds a todo task\n"
                + "-> deadline <task_name> /by <D MM YY HHmm OR D MMM YY HHmm> - Adds a deadline task\n"
                + "-> event <task_name> /from <D MM YY HHmm OR D MMM YY HHmm> "
                + "/to <D MM YY HHmm OR D MMM YY HHmm> - Adds an event task\n"
                + "-> list - Lists all tasks\n"
                + "-> mark <task_number> - Marks a task as done\n"
                + "-> unmark <task_number> - Marks a task as not done\n"
                + "-> delete <task_number> - Deletes a task\n"
                + "-> find <keyword(s)> - Finds all tasks containing at least one of the keyword(s)\n"
                + "-> help - Displays this help message\n"
                + "-> bye - Exits the application";
    }
}
