package cortana.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToStringIncludesFromAndTo() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 10, 14, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 10, 16, 0);
        Event event = new Event("Project Meeting", from, to);

        String str = event.toString();
        assertTrue(str.contains("[E]"));
        assertTrue(str.contains("Project Meeting"));
        assertTrue(str.contains("from"));
        assertTrue(str.contains("to"));
    }
}
