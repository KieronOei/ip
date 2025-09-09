package cortana.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void testShowOutputFormats() {
        Ui ui = new Ui();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ui.showOutput("Hello World");

        String printed = out.toString();
        assertTrue(printed.contains("Hello World"));
        assertTrue(printed.contains("________________________________________________________________________________"));

        System.setOut(System.out); // reset
    }
}
