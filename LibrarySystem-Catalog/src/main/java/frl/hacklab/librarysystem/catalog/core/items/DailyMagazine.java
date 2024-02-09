package frl.hacklab.librarysystem.catalog.core.items;

import java.time.LocalDate;

public class DailyMagazine extends MagazineImpl {

  public DailyMagazine(String isnn, int appearanceNumber, String title,
      LocalDate publicationDate, String publisher, String genre, String ageRating,
      String copyEditor, BorrowingStatus isOnloan) {
    super(isnn, appearanceNumber, title, publicationDate, publisher, genre, ageRating, copyEditor, isOnloan);
  }


  /**
   * @return the appearance number of the magazine
   */
  public int getNumber(){
    return getAppearanceNumber();
  }

  @Override
  public String getOverviewItemText() {
    return String.format("%s ISNN: %s Nummer: %d The magazine is %s", getTitle(), getIssn(), getNumber(), getLoanStatus());
  }

}
