package frl.hacklab.librarysystem.catalog.infrastructure.services;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.application.usecases.GetLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.services.FrontendService;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository.RepositoryFactoryImpl;
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

  @Autowired
  private ConversionService conversionService;

  /**
   * @param repositoryFactory The factory for creating repositories.
   */
  @Autowired
  protected MagazineFrontendService(RepositoryFactoryImpl repositoryFactory) {
    useCase = new GetLibraryItemUseCase(repositoryFactory);
  }

  /**
   * This method retrieves a library item and converts it to a DTO.
   *
   * @param itemClass The class of the library item to be retrieved.
   * @param id The id of the library item to be retrieved.
   * @return The DTO of the retrieved item.
   */
  @Override
  public MagazineDto getItem(Class<? extends LibraryItemDto> itemClass, Long id){
    LibraryItem item = useCase.getItem(itemClass, id);
    logger.trace("Retrieved item: {}", item);
    MagazineDto dto = conversionService.convert(item, MagazineDto.class);
    logger.trace("Converted item: {}", dto);
    return dto;
  }
}