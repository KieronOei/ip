package cortana.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

class FindCommandTest {

    private TaskList mockTaskList;
    private FileHandler mockFileHandler;
    private FindCommand findCommand;

    @BeforeEach
    void setUp() {
        mockTaskList = mock(TaskList.class);
        mockFileHandler = mock(FileHandler.class);
        findCommand = new FindCommand("book");
    }

    @Test
    void testExecute_showsMatchingTasks() throws CortanaException, IOException {
        TaskList filteredTasks = new TaskList();
        // Optionally add mock tasks to filteredTasks or create mock of TaskList with toString() stubbed
        when(mockTaskList.find("book")).thenReturn(filteredTasks);
        when(filteredTasks.toString()).thenReturn(
                "\n\t1. [T][X] read book\n\t2. [D][X] return book (by: June 6th)");

        findCommand.execute(mockTaskList, mockFileHandler);

        verify(mockTaskList).find("book");
    }
}
