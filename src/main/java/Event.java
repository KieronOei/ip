import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // Format by into a string e.g 05 SEP 25 0430
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        String textFrom = from.format(formatter);
        String textTo = to.format(formatter);
        return "[E]" + super.toString() + " (from: " + textFrom + " to: " + textTo + ")";
    }
}
