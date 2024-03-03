package frl.hacklab.librarysystem.catalog.domain.model;

import java.time.LocalDate;

public interface LibraryItem {

  long getId();
  String getTitle();

  String getPublisher();

  String getGenre();

  LocalDate getPublicationDate();

  String getAgeRating();

}
