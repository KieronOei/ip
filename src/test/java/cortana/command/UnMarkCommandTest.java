package cortana.command;


// import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

//import cortana.exception.CortanaException;
// import org.junit.jupiter.api.Test;

// import java.io.IOException;

public class UnMarkCommandTest {

    private TaskList tasks;
    private Ui ui;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        ui = mock(Ui.class);
        fileHandler = mock(FileHandler.class);
    }
    //    TODO: FIX THIS
    //    @Test
    //    public void testExecute() throws CortanaException, IOException {
    //        UnMarkCommand unmarkCmd = new UnMarkCommand(3);
    //        unmarkCmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveMarkValue(tasks, 3, "0");
    //    }
}
