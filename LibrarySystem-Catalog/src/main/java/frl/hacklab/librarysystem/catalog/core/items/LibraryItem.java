package frl.hacklab.librarysystem.catalog.core.items;

import java.time.LocalDate;

public interface LibraryItem {

  String getTitle();

  String getPublisher();

  String getGenre();

  LocalDate getPublicationDate();

  String getAgeRating();

}
