package cortana.core;

import cortana.command.*;
import cortana.exception.CortanaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_todoCommand() throws CortanaException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parse_deadlineCommand() throws CortanaException {
        Command cmd = Parser.parse("deadline submit assignment /by 4 9 25 2359");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parse_eventCommand() throws CortanaException {
        Command cmd = Parser.parse("event project meeting /from 4 9 25 1500 /to 4 9 25 1600");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parse_markCommand() throws CortanaException {
        Command cmd = Parser.parse("mark 1");
        assertTrue(cmd instanceof MarkCommand);
    }

    @Test
    void parse_unmarkCommand() throws CortanaException {
        Command cmd = Parser.parse("unmark 1");
        assertTrue(cmd instanceof UnMarkCommand);
    }

    @Test
    void parse_deleteCommand() throws CortanaException {
        Command cmd = Parser.parse("delete 1");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    void parse_listCommand() throws CortanaException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }

    @Test
    void parse_byeCommand() throws CortanaException {
        Command cmd = Parser.parse("bye");
        assertTrue(cmd instanceof ExitCommand);
    }

    @Test
    void parse_unknownCommand_throws() {
        assertThrows(CortanaException.class, () -> Parser.parse("unknowncmd"));
    }

    @Test
    void parse_invalidNumberFormat_throws() {
        assertThrows(CortanaException.class, () -> Parser.parse("mark notANumber"));
    }
}
