package cortana.task;

import cortana.exception.CortanaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddAndSize() {
        assertEquals(0, taskList.size());
        taskList.add(new ToDo("Task 1"));
        assertEquals(1, taskList.size());
    }

    @Test
    void testDelete() throws CortanaException {
        taskList.add(new ToDo("Task A"));
        String msg = taskList.delete(1);
        assertTrue(msg.contains("Deleted:"));
        assertEquals(0, taskList.size());
        assertThrows(CortanaException.class, () -> taskList.delete(1));
    }

    @Test
    void testMarkUnmark() throws CortanaException {
        taskList.add(new ToDo("Task B"));
        assertTrue(taskList.mark(1).contains("marked as done"));
        assertTrue(taskList.unmark(1).contains("marked as not done"));
        assertThrows(CortanaException.class, () -> taskList.mark(10));
    }

    @Test
    void testToStringEmptyAndNotEmpty() {
        assertTrue(taskList.toString().contains("no items"));
        taskList.add(new ToDo("Task 1"));
        assertTrue(taskList.toString().contains("tasks in your list"));
    }
}
