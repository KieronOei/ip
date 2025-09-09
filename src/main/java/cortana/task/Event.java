package cortana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents an event task with a start and end date/time. */
public class Event extends Task {

  /** The starting date and time of the event. */
  private LocalDateTime from;

  /** The ending date and time of the event. */
  private LocalDateTime to;

  /**
   * Constructs an cortana.task.Event task with the specified name, start time, and end time.
   *
   * @param name The name or description of the event.
   * @param from The LocalDateTime when the event starts.
   * @param to The LocalDateTime when the event ends.
   */
  public Event(String name, LocalDateTime from, LocalDateTime to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  /**
   * Returns a string representation of the cortana.task.Event task, including task type, completion
   * status, name, start, and end times.
   *
   * @return A formatted string representing the event task.
   */
  @Override
  public String toString() {
    // Format date/time as e.g. 05 SEP 25 0430
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
    String textFrom = from.format(formatter);
    String textTo = to.format(formatter);
    return "[E]" + super.toString() + " (from: " + textFrom + " to: " + textTo + ")";
  }
}
