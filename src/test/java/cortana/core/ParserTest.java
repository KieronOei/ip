package cortana.core;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cortana.command.AddCommand;
import cortana.command.Command;
import cortana.command.DeleteCommand;
import cortana.command.ExitCommand;
import cortana.command.FindCommand;
import cortana.command.ListCommand;
import cortana.command.MarkCommand;
import cortana.command.UnMarkCommand;
import cortana.exception.CortanaException;

public class ParserTest {

    @Test
    void parse_todoCommand() throws CortanaException {
        Command cmd = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parse_deadlineCommand() throws CortanaException {
        Command cmd = Parser.parse("deadline submit assignment /by 4 9 25 2359");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parse_eventCommand() throws CortanaException {
        Command cmd = Parser.parse("event project meeting /from 4 9 25 1500 /to 4 9 25 1600");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parse_markCommand() throws CortanaException {
        Command cmd = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    void parse_unmarkCommand() throws CortanaException {
        Command cmd = Parser.parse("unmark 1");
        assertInstanceOf(UnMarkCommand.class, cmd);
    }

    @Test
    void parse_deleteCommand() throws CortanaException {
        Command cmd = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    void parse_findCommand() throws CortanaException {
        Command cmd = Parser.parse("find");
        assertInstanceOf(FindCommand.class, cmd);
    }

    @Test
    void parse_listCommand() throws CortanaException {
        Command cmd = Parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
    }

    @Test
    void parse_byeCommand() throws CortanaException {
        Command cmd = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, cmd);
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
