package cortana.task;

import cortana.exception.CortanaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task todo;

    @BeforeEach
    void setUp() {
        todo = new ToDo("Read book");
    }

    @Test
    void testMarkUnmark() throws CortanaException {
        assertFalse(todo.toString().contains("X"));

        todo.mark();
        assertTrue(todo.toString().contains("X"));

        assertThrows(CortanaException.class, () -> todo.mark());

        todo.unmark();
        assertFalse(todo.toString().contains("X"));

        assertThrows(CortanaException.class, () -> todo.unmark());
    }
}
