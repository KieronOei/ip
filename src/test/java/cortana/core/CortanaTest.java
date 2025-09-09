package cortana.core;

import cortana.command.Command;
import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;
import cortana.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

public class CortanaTest {

    private Cortana cortana;
    private Ui ui;
    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        ui = mock(Ui.class);
        fileHandler = mock(FileHandler.class);
        cortana = new Cortana(ui, fileHandler);
    }

    @Test
    void testInitialization_loadsTasks() throws IOException, CortanaException {
        cortana.initialize();
        verify(fileHandler).ensureFileExists();
        verify(fileHandler).checkAndPrepareFile();
        verify(fileHandler).loadTasks();
        verify(ui).showOutput(contains("Data has been loaded"));
        verify(ui).showGreeting();
    }
}
