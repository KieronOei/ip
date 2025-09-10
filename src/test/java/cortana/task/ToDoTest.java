package cortana.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testToString() {
        ToDo todo = new ToDo("Test ToDo");
        String str = todo.toString();
        assertTrue(str.contains("[T]"));
        assertTrue(str.contains("Test ToDo"));
    }
}
