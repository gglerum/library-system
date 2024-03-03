package frl.hacklab.librarysystem.catalog.infrastructure.services;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.application.usecases.GetLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.services.FrontendService;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * This class represents the frontend service for magazines.
 */
@Service
public class MagazineFrontendService implements FrontendService {
  private final Logger logger = LoggerFactory.getLogger(MagazineFrontendService.class);
  private final GetLibraryItemUseCase useCase;
  private final ConversionService conversionService;

  /**
   * @param useCase the use case to use to retrieve the item
   * @param conversionService the conversion service to use to convert the item to a DTO
   */
  @Autowired
  protected MagazineFrontendService(GetLibraryItemUseCase useCase, ConversionService conversionService) {
    this.useCase = useCase;
    this.conversionService = conversionService;
  }

  /**
   * This method retrieves a library item and converts it to a DTO.
   *
   * @param itemClass The class of the library item to be retrieved.
   * @param id The id of the library item to be retrieved.
   * @return The DTO of the retrieved item.
   * @throws NoSuchElementException when the use case could not find the item
   */
  @Override
  public MagazineDto getItem(Class<? extends LibraryItemDto> itemClass, Long id){
    LibraryItem item;
    try {
      item = useCase.getItem(itemClass, id);
    }catch(NoSuchElementException e){
      logger.error("Item of type {} not found: {}", itemClass.getSimpleName(), id);
      throw new NoSuchElementException("%s with id %d not found".formatted(itemClass.getSimpleName(), id), e);
    }

    logger.trace("Retrieved item: {}", item);
    MagazineDto dto = conversionService.convert(item, MagazineDto.class);
    logger.trace("Converted item: {}", dto);
    return dto;
  }
}