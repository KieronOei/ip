package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ExitCommandTest {

    private TaskList tasks;
    private Ui ui;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        ui = mock(Ui.class);
        fileHandler = mock(FileHandler.class);
    }

    @Test
    public void testExecute() {
        ExitCommand exitCmd = new ExitCommand();
        exitCmd.execute(tasks, ui, fileHandler);
        verify(ui).showFarewell();
        assert(exitCmd.isExit());
    }
}
