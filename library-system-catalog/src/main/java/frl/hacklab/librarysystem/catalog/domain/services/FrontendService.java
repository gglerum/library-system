package frl.hacklab.librarysystem.catalog.domain.services;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;

public interface FrontendService {

  LibraryItemDto getItem(Class<? extends LibraryItemDto> itemClass, Long id);

}
