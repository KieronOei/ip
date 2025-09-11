package cortana.command;

// import cortana.exception.CortanaException;

import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;

import cortana.storage.FileHandler;
import cortana.task.TaskList;

//  import org.junit.jupiter.api.Test;
// import java.io.IOException;

public class MarkCommandTest {

    private TaskList tasks;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        fileHandler = mock(FileHandler.class);
    }
    //  TODO: FIX THIS
    //    @Test
    //    public void testExecute() throws CortanaException, IOException {
    //        MarkCommand markCmd = new MarkCommand(2);
    //        markCmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveMarkValue(tasks, 2, "1");
    //    }
}
