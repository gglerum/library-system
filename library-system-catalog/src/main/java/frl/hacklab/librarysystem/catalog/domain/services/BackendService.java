package frl.hacklab.librarysystem.catalog.domain.services;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;

public interface BackendService extends FrontendService {
  LibraryItemDto addItem(LibraryItemDto item);
  LibraryItemDto updateItem(LibraryItemDto item);
  void removeItem(LibraryItemDto item);
}
