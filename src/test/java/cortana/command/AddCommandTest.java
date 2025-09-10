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

public class AddCommandTest {

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
    //    public void testExecuteToDo() throws CortanaException, IOException {
    //        AddCommand cmd = new AddCommand("Test todo");
    //        cmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveToDo(eq(tasks), anyString());
    //  TODO fix
    //    @Test
    //    public void testExecuteDeadline() throws CortanaException, IOException {
    //        AddCommand cmd = new AddCommand("Test deadline", null);
    //        cmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveDeadline(eq(tasks), anyString());
    //    }
    //  TODO FIX
    //    @Test
    //    public void testExecuteEvent() throws CortanaException, IOException {
    //        AddCommand cmd = new AddCommand("Test event", null, null);
    //        cmd.execute(tasks, ui, fileHandler);
    //        verify(ui).showOutput(anyString());
    //        verify(fileHandler).saveEvent(eq(tasks), anyString());
    //    }
}
