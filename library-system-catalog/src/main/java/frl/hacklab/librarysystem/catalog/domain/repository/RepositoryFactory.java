package frl.hacklab.librarysystem.catalog.domain.repository;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;

public interface RepositoryFactory {
  ItemRepository getRepository(LibraryItem item);

  default ItemRepository getRepository(Class<? extends LibraryItemDto> itemClass){
    throw new UnsupportedOperationException("Not implemented");
  }
}
