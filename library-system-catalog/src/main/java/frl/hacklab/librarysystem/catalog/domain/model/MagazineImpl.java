package frl.hacklab.librarysystem.catalog.domain.model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public abstract class MagazineImpl extends Literature {

  private final String isnn;
  private final int appearanceNumber;

  MagazineImpl(long id, String isnn, int appearanceNumber, String title, LocalDate publicationDate, String publisher, String genre,
      String ageRating, String copyEditor, BorrowingStatus isOnLoan){
    super(id, title, publicationDate, publisher, genre, ageRating, copyEditor, isOnLoan);
    this.isnn = isnn;
    this.appearanceNumber = appearanceNumber;
  }

  /**
   * @return the isnn of the magazine
   */
  String getIssn() {
    return isnn;
  }

  /**
   * @return appearance number of the magazine
   */
  int getAppearanceNumber() {
    return appearanceNumber;
  }

  @Override
  public boolean matchesSearch(String search) {
    String searchTerm = search.toLowerCase(Locale.ROOT);
    return getTitle().toLowerCase(Locale.ROOT).contains(searchTerm);
  }

  @Override
  public int compareTo(LibraryItem o) {
    if(!(o instanceof MagazineImpl)) {
      return super.compareTo(o);
    }
    return Comparator.comparing(MagazineImpl::getPublisher)
        .thenComparing(MagazineImpl::getTitle)
        .thenComparing(MagazineImpl::getAppearanceNumber)
        .compare(this, (MagazineImpl) o);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof MagazineImpl && compareTo((LibraryItem) obj) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), isnn, appearanceNumber);
  }

}
