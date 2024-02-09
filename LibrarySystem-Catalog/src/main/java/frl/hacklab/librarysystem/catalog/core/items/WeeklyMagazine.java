package frl.hacklab.librarysystem.catalog.core.items;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeeklyMagazine extends MagazineImpl {

  public WeeklyMagazine(String isnn, int appearanceNumber, String title,
      LocalDate publicationDate, String publisher, String genre, String ageRating,
      String copyEditor, BorrowingStatus isOnLoan){
    super(isnn, appearanceNumber, title, publicationDate, publisher, genre, ageRating, copyEditor, isOnLoan);
  }

  /**
   * @return the week number of the year
   */
  public int getWeekNumber(){
    WeekFields weekFields = WeekFields.of(Locale.getDefault());
    return getPublicationDate().get(weekFields.weekOfWeekBasedYear());
  }

  @Override
  public String getOverviewItemText() {
    return String.format("%s ISNN: %s Week Nummer: %d The magazine is %s", getTitle(), getIssn(), getWeekNumber(), getLoanStatus());
  }
}
