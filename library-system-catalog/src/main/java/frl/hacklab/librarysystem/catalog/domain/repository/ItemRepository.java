package frl.hacklab.librarysystem.catalog.domain.repository;

import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import java.util.Optional;

public interface ItemRepository {

  Optional<? extends LibraryItem> findById(Long id);

  LibraryItem save(LibraryItem item);

  void delete(LibraryItem item);

  Iterable<? extends LibraryItem> findAll();
}
