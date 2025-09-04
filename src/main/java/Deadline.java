import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        // Format by into a string e.g 05 SEP 25 0430
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        String text = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + text + ")";
    }
}
