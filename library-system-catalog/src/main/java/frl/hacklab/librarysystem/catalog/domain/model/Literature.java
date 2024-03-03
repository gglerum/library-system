package frl.hacklab.librarysystem.catalog.domain.model;

import java.time.LocalDate;

public abstract class Literature extends LibraryItemImpl {
  private final String copyEditor;

  Literature(long id, String title, LocalDate publicationDate, String publisher, String genre, String ageRating, String copyEditor, BorrowingStatus isOnLoan) {
    super(id, title, publicationDate, publisher, genre, ageRating, isOnLoan);
    this.copyEditor = copyEditor;
  }

  /**
   * @return the copy editor
   */
  public String getCopyEditor() {
    return copyEditor;
  }
}
