package cortana.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class UiTest {

    @Test
    void testShowOutputFormats() {
        Ui ui = new Ui();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ui.showOutput("Hello World");

        String printed = out.toString();
        assertTrue(printed.contains("Hello World"));
        final String line = "________________________________________________________________________________";
        assertTrue(printed.contains(line));


        System.setOut(System.out); // reset
    }
}
