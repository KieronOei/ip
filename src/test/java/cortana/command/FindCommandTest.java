package cortana.command;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.Task;
import cortana.task.TaskList;
import cortana.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class FindCommandTest {

  private TaskList mockTaskList;
  private Ui mockUi;
  private FileHandler mockFileHandler;
  private FindCommand findCommand;

  @BeforeEach
  void setUp() {
    mockTaskList = mock(TaskList.class);
    mockUi = mock(Ui.class);
    mockFileHandler = mock(FileHandler.class);
    findCommand = new FindCommand("book");
  }

  @Test
  void testExecute_showsMatchingTasks() throws CortanaException, IOException {
    TaskList filteredTasks = new TaskList();
    // Optionally add mock tasks to filteredTasks or create mock of TaskList with toString() stubbed
    when(mockTaskList.find("book")).thenReturn(filteredTasks);
    when(filteredTasks.toString()).thenReturn(
            "\n\t1. [T][X] read book\n\t2. [D][X] return book (by: June 6th)");

    findCommand.execute(mockTaskList, mockUi, mockFileHandler);

    verify(mockTaskList).find("book");
    verify(mockUi).showOutput("Here are the matching tasks in your list:\n\t" + filteredTasks.toString());
  }
}
