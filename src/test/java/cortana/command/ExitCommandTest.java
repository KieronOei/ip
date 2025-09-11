package cortana.command;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.storage.FileHandler;
import cortana.task.TaskList;

public class ExitCommandTest {

    private TaskList tasks;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        fileHandler = mock(FileHandler.class);
    }

    @Test
    public void testExecute() {
        ExitCommand exitCmd = new ExitCommand();
        exitCmd.execute(tasks, fileHandler);
        assert (exitCmd.isExit());
    }
}
