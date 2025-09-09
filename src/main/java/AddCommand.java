import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handles adding of new tasks such as ToDo, Deadline, and Event.
 * Supports different constructors for each task type.
 */
public class AddCommand implements Command {
    private final CommandType type;
    private final String taskName;
    private final LocalDateTime deadline;  // nullable, only for deadline
    private final LocalDateTime from;      // nullable, only for event
    private final LocalDateTime to;        // nullable, only for event

    /**
     * Constructs an AddCommand for a ToDo task.
     *
     * @param taskName The name of the ToDo task
     */
    public AddCommand(String taskName) {
        this.type = CommandType.TODO;
        this.taskName = taskName;
        this.deadline = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs an AddCommand for a Deadline task.
     *
     * @param taskName The name of the Deadline task
     * @param deadline The deadline date and time
     */
    public AddCommand(String taskName, LocalDateTime deadline) {
        this.type = CommandType.DEADLINE;
        this.taskName = taskName;
        this.deadline = deadline;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs an AddCommand for an Event task.
     *
     * @param taskName The name of the Event task
     * @param from The event start date and time
     * @param to The event end date and time
     */
    public AddCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.type = CommandType.EVENT;
        this.taskName = taskName;
        this.deadline = null;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add command, adding the task to the task list,
     * displaying output, and saving the task to persistent storage.
     *
     * @param tasks The TaskList to add to
     * @param ui The UI for output display
     * @param fileHandler The FileHandler for saving task data
     * @throws CortanaException if an invalid task type is encountered
     * @throws IOException if an I/O error occurs while saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, FileHandler fileHandler) throws CortanaException, IOException {
        Task task;
        switch (type) {
        case TODO:
            task = new ToDo(taskName);
            break;
        case DEADLINE:
            task = new Deadline(taskName, deadline);
            break;
        case EVENT:
            task = new Event(taskName, from, to);
            break;
        default:
            throw new CortanaException("Invalid task type in AddCommand");
        }
        ui.showOutput(tasks.add(task));
        // call appropriate save method
        switch (type) {
        case TODO:
            fileHandler.saveToDo(tasks, task.toString());
            break;
        case DEADLINE:
            fileHandler.saveDeadline(tasks, task.toString());
            break;
        case EVENT:
            fileHandler.saveEvent(tasks, task.toString());
            break;
        }
    }
}
