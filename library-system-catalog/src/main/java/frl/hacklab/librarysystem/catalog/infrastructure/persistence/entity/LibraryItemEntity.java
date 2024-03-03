package frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity;

import frl.hacklab.librarysystem.catalog.domain.model.BorrowingStatus;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class LibraryItemEntity implements LibraryItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String ageRating;
  @NonNull
  private String title;
  private LocalDate publicationDate;
  private String publisher;
  private String genre;
  private BorrowingStatus isOnLoan;
}
