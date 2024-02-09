package frl.hacklab.librarysystem.catalog.core.items;

import java.util.Optional;

public interface ItemRepository {

  Optional<? extends LibraryItem> findById(Long id);

  LibraryItem save(LibraryItem item);

  void delete(LibraryItem item);
}
