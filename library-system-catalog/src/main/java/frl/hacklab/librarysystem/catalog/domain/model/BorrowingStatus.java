package frl.hacklab.librarysystem.catalog.domain.model;

public enum BorrowingStatus {
  AVAILABLE("Available"),
  ON_LOAN("On loan");

  private final String label;

  BorrowingStatus(String label) {
    this.label = label;
  }

  public String label(){
    return label;
  }
}
