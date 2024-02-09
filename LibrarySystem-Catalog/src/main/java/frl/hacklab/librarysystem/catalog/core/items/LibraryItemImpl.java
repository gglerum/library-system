package frl.hacklab.librarysystem.catalog.core.items;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public abstract class LibraryItemImpl implements LibraryItem, Searchable, Comparable<LibraryItem> {

  private final String ageRating;
  private final String title;
  private final LocalDate publicationDate;
  private final String publisher;
  private final String genre;
  private BorrowingStatus isOnLoan;

  LibraryItemImpl(String title, LocalDate publicationDate, String publisher, String genre,
      String ageRating, BorrowingStatus isOnLoan){
    this.title = title;
    this.publicationDate = publicationDate;
    this.publisher = publisher;
    this.genre = genre;
    this.ageRating = ageRating;
    this.isOnLoan = isOnLoan;
  }

  /**
   * @return the title
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   * @return the publisher
   */
  @Override
  public String getPublisher() {
    return publisher;
  }

  /**
   * @return the genre
   */
  @Override
  public String getGenre() {
    return genre;
  }

  /**
   * @return the publication date
   */
  @Override
  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  /**
   * @return the age rating
   */
  @Override
  public String getAgeRating() {
    return ageRating;
  }


  /**
   * @return year of publication
   */
  public int getPublicationYear(){
    return publicationDate.getYear();
  }

  /**
   * Get loan status
   */
  public String getLoanStatus(){
    return isOnLoan.label();
  }

  /**
   * @return true if item is on loan, false if available
   */
  public boolean isOnLoan(){
    return BorrowingStatus.ON_LOAN == isOnLoan;
  }

  /**
   * Borrow the libraryItem
   * @throws RuntimeException when item is already on loan
   */
  public final void borrowItem() throws RuntimeException {
    if(BorrowingStatus.ON_LOAN == isOnLoan){
      throw new RuntimeException("Item is already on loan");
    }
    isOnLoan = BorrowingStatus.ON_LOAN;
  }
  /**
   * Return the libraryItem
   * @throws RuntimeException when item is not on loan
   */
  public final void returnItem() throws RuntimeException {
    if(BorrowingStatus.ON_LOAN != isOnLoan){
      throw new RuntimeException("Item is not on loan");
    }
    isOnLoan = BorrowingStatus.AVAILABLE;
  }

  /**
   * @return the type of the item, different for each sub class
   */
  public String getType() {
    return getClass().getSimpleName();
  }

  @Override
  public int compareTo(LibraryItem o) {
    return Comparator.comparing(LibraryItem::getTitle)
        .compare(this, o);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof LibraryItemImpl && compareTo((LibraryItem) obj) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, publicationDate, publisher);
  }

  public abstract String getOverviewItemText();
}
