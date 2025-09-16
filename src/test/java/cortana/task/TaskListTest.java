package cortana.task;

import static cortana.core.Parser.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.exception.CortanaException;

/**
 * Unit tests for the TaskList class.
 * Tests adding, deleting tasks and exception handling.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a fresh TaskList instance before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests that adding any task increases the size of the task list.
     * Adding a task should not throw a CortanaException.
     */
    @Test
    void add_anyTask_sizeShouldIncrease() {
        taskList.add(new ToDo("Read book"));
        assertEquals(1, taskList.size());

        try {
            taskList.add(new Deadline("Return book", parseDate("16 9 25 1600")));
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertEquals(2, taskList.size());

        try {
            taskList.add(new Event("Report book findings", parseDate("16 9 25 1400"), parseDate("16 9 25 1500")));
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertEquals(3, taskList.size());
    }

    /**
     * Tests that adding a task returns a string containing an added message and the task count.
     */
    @Test
    void add_anyTask_returnStringShouldContainAddedMessage() {
        String result = taskList.add(new ToDo("Read book"));
        assertTrue(result.contains("Added:"));
        assertTrue(result.contains("1"));
    }

    /**
     * Tests that deleting a valid task reduces the task list size.
     * Deleting a task should not throw a CortanaException.
     */
    @Test
    void delete_validTaskNumber_sizeShouldDecrease() {
        taskList.add(new ToDo("Read book"));
        try {
            taskList.delete(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertEquals(0, taskList.size());
    }

    /**
     * Tests that deleting with invalid task numbers throws a CortanaException.
     * Covers edge cases like 0-index input and inputs beyond current size.
     */
    @Test
    void delete_invalidTaskNumber_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        // User may think task number starts from 0
        assertThrows(CortanaException.class, () -> taskList.delete(0));
        // User may mistakenly think there are more items than present
        assertThrows(CortanaException.class, () -> taskList.delete(2));
    }

    /**
     * Tests that deleting a valid task returns a string containing a deleted message.
     * Deleting a task should not throw a CortanaException.
     */
    @Test
    void delete_validTaskNumber_returnStringShouldContainDeletedMessage() {
        taskList.add(new ToDo("Read book"));
        String result = null;
        try {
            result = taskList.delete(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertTrue(result.contains("Deleted:"));
        assertTrue(result.contains("0"));
    }
}
