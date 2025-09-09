package cortana.core;

import cortana.command.*;
import cortana.exception.CortanaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Parses user input strings and converts them into cortana.command.Command objects. */
public class Parser {

  /**
   * Parses the full user input into a cortana.command.Command object.
   *
   * @param fullCommand The raw input string from the user.
   * @return The cortana.command.Command object representing the user input.
   * @throws CortanaException if the input is invalid or cannot be parsed.
   */
  public static Command parse(String fullCommand) throws CortanaException {
    // Split input by '/' to separate command and parameters
    String[] splitBySlash = fullCommand.split("/");

    // Split the first part by spaces to get the command and first argument
    String[] firstTokenSplit = splitBySlash[0].trim().split(" ");
    CommandType commandType = CommandType.fromString(firstTokenSplit[0]);

    try {
      switch (commandType) {
        case TODO:
          if (firstTokenSplit.length < 2) throw new CortanaException("Specify task name");
          String todoName = fullCommand.substring(firstTokenSplit[0].length()).trim();
          return new AddCommand(todoName);

        case DEADLINE:
          if (firstTokenSplit.length < 2 || splitBySlash.length < 2)
            throw new CortanaException("Specify task name and deadline with /by");
          String deadlineName =
              firstTokenSplit[1] + (splitBySlash.length > 2 ? " " + splitBySlash[2] : "");
          LocalDateTime deadlineDate = parseDate(splitBySlash[1].trim().substring(3));
          return new AddCommand(deadlineName, deadlineDate);

        case EVENT:
          if (firstTokenSplit.length < 2 || splitBySlash.length < 3)
            throw new CortanaException("Specify task name and /from and /to times");
          String eventName =
              firstTokenSplit[1] + (splitBySlash.length > 3 ? " " + splitBySlash[3] : "");
          LocalDateTime fromDate = parseDate(splitBySlash[1].trim().substring(5));
          LocalDateTime toDate = parseDate(splitBySlash[2].trim().substring(3));
          return new AddCommand(eventName, fromDate, toDate);

        case MARK:
          if (firstTokenSplit.length < 2) throw new CortanaException("Specify task number to mark");
          int maskNumber = Integer.parseInt(firstTokenSplit[1]);
          return new MarkCommand(maskNumber);

        case UNMARK:
          if (firstTokenSplit.length < 2)
            throw new CortanaException("Specify task number to unmark");
          int unMaskNumber = Integer.parseInt(firstTokenSplit[1]);
          return new UnMarkCommand(unMaskNumber);

        case DELETE:
          if (firstTokenSplit.length < 2)
            throw new CortanaException("Specify task number to delete");
          int deleteIndex = Integer.parseInt(firstTokenSplit[1]);
          return new DeleteCommand(deleteIndex);

        case LIST:
          return new ListCommand();

        case BYE:
          return new ExitCommand();

        default:
          throw new CortanaException("I don't understand the command.");
      }
    } catch (NumberFormatException e) { // Catch the case when parseInt processes non-digits
      throw new CortanaException("Invalid number format.");
    }
  }

  /**
   * Parses a date string into a LocalDateTime object. Supports multiple date formats.
   *
   * @param dateString The string representing the date/time.
   * @return A LocalDateTime object parsed from the string.
   * @throws CortanaException if the string cannot be parsed into a valid date.
   */
  private static LocalDateTime parseDate(String dateString) throws CortanaException {
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d M yy HHmm");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yy HHmm");
    try {
      return LocalDateTime.parse(dateString, formatter1);
    } catch (DateTimeParseException e) {
      try {
        return LocalDateTime.parse(dateString, formatter2);
      } catch (DateTimeParseException ex) {
        throw new CortanaException("Invalid date format. Use d M yy HHmm or d MMM yy HHmm");
      }
    }
  }
}
