package cortana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time by which the task should be completed.
     */
    private LocalDateTime by;

    /**
     * Constructs a cortana.task.Deadline task with the specified name and deadline time.
     *
     * @param name The name or description of the deadline task.
     * @param by The LocalDateTime by which the task is due.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a string representation of the cortana.task.Deadline task,
     * including the task type, completion status, name, and formatted deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        // Format 'by' into a string e.g., 05 SEP 25 0430
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        String text = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + text + ")";
    }
}
