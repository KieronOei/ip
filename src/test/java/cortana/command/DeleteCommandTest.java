package cortana.command;

//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;

//import static org.mockito.Mockito.eq;


//import java.io.IOException;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

// import cortana.exception.CortanaException;

public class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() {
        tasks = mock(TaskList.class);
        ui = mock(Ui.class);
        fileHandler = mock(FileHandler.class);
    }
    //  TODO FIX
    //    @Test
    //    public void testExecute() throws CortanaException, IOException {
    //        DeleteCommand delCmd = new DeleteCommand(1);
    //        delCmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveDelete(tasks, 1);
    //    }
}
