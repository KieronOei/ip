package cortana.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;

public class CortanaTest {

    private Cortana cortana;
    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        fileHandler = mock(FileHandler.class);
        cortana = new Cortana(fileHandler);
    }

    @Test
    void testInitialization_loadsTasks() throws IOException, CortanaException {
        cortana.initialize();
        verify(fileHandler).ensureFileExists();
        verify(fileHandler).checkAndPrepareFile();
        verify(fileHandler).loadTasks();
    }
}
