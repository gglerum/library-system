package frl.hacklab.librarysystem.catalog;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom log appender for testing purposes.
 * This appender collects log events into a static list that can be queried and cleared.
 *
 * The static list can be accessed directly during testing to check for log events.
 */
public class TestLogAppender extends ListAppender<ILoggingEvent> {

  // A static list to hold the log events
  private static final List<ILoggingEvent> LIST = new ArrayList<>();

  /**
   * Clears the list of log events.
   */
  public static void reset(){
    LIST.clear();
  }

  /**
   * Appends a log event to the list.
   * @param e The log event to append.
   */
  @Override
  protected void append(ILoggingEvent e){
    LIST.add(e);
  }

  /**
   * Checks if a specific log message is present in the list of log events.
   * @param logMessage The log message to search for.
   * @return true if the log message is found, false otherwise.
   */
  public static boolean contains(String logMessage) {
    return LIST.stream().anyMatch(event -> event.toString().contains(logMessage));
  }

  /**
   * Checks if the list of log events has any entries.
   * @return true if the list has entries, false otherwise.
   */
  public static boolean hasEntries(){
    return !LIST.isEmpty();
  }
}