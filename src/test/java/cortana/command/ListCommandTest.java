package cortana.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.storage.FileHandler;
import cortana.task.TaskList;

public class ListCommandTest {

    private TaskList tasks;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        fileHandler = mock(FileHandler.class);
    }

    @Test
    public void testExecute() {
        ListCommand listCmd = new ListCommand();
        listCmd.execute(tasks, fileHandler);
    }
}
