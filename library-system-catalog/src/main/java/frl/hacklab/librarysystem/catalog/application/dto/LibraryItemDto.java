package frl.hacklab.librarysystem.catalog.application.dto;

import frl.hacklab.librarysystem.catalog.domain.model.BorrowingStatus;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
@ToString
public class LibraryItemDto {
  private long id;
  private String ageRating;
  private String title;
  private LocalDate publicationDate;
  private String publisher;
  private String genre;
  private BorrowingStatus isOnLoan;
}
