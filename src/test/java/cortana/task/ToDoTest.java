package cortana.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        ToDo todo = new ToDo("Test ToDo");
        String str = todo.toString();
        assertTrue(str.contains("[T]"));
        assertTrue(str.contains("Test ToDo"));
    }
}
