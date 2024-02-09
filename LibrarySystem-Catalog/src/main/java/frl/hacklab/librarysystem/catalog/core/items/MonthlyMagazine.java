package frl.hacklab.librarysystem.catalog.core.items;

import java.time.LocalDate;
import java.util.Locale;

public class MonthlyMagazine extends MagazineImpl {

  private final String name;

  public MonthlyMagazine(String isnn, int appearanceNumber, String title, String name,
      LocalDate publicationDate, String publisher, String genre, String ageRating,
      String copyEditor, BorrowingStatus isOnLoan) {
    super(isnn, appearanceNumber, title, publicationDate, publisher, genre, ageRating, copyEditor, isOnLoan);
    this.name = name;
  }

  /**
   * @return the name / subtitle of the magazine
   */
  public String getName() {
    return name;
  }

  /**
   * @return month number of publication date
   */
  public int getMonthNumber(){
    return getPublicationDate().getMonthValue();
  }

  @Override
  public String getOverviewItemText() {
    return String.format("%s ISNN: %s Maand Nummer: %d The magazine is %s", getTitle(), getIssn(), getMonthNumber(), getLoanStatus());
  }

  @Override
  public boolean matchesSearch(String search) {
    return super.matchesSearch(search)
        || name.toLowerCase(Locale.ROOT).contains(search.toLowerCase(
        Locale.ROOT));
  }
}
