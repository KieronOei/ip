package cortana.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToStringFormatsDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 9, 10, 16, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);

        String str = deadline.toString();
        assertTrue(str.contains("[D]"));
        assertTrue(str.contains("Submit report"));
        assertTrue(str.matches(".*\\(by: \\d{2} \\w{3} \\d{2} \\d{4}\\).*") || str.contains("09 10"));
    }
}
