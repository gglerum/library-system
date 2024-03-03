package frl.hacklab.librarysystem.catalog.infrastructure.services;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.application.usecases.AddLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.application.usecases.GetLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.application.usecases.RemoveLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.application.usecases.UpdateLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.services.BackendService;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository.RepositoryFactoryImpl;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

/**
 * This class represents the backend service for managing magazines in the library system.
 */
public class MagazineBackendService extends MagazineFrontendService implements BackendService {
  private final Logger logger = LoggerFactory.getLogger(MagazineBackendService.class);
  private final AddLibraryItemUseCase addLibraryItemUseCase;
  private final UpdateLibraryItemUseCase updateLibraryItemUseCase;
  private final RemoveLibraryItemUseCase removeLibraryItemUseCase;
  private final ConversionService conversionService;

  /**
   * @param repositoryFactory The factory for creating repositories.
   */
  @Autowired
  MagazineBackendService(ConversionService conversionService, GetLibraryItemUseCase getLibraryItemUseCase, RepositoryFactoryImpl repositoryFactory){
    super(getLibraryItemUseCase, conversionService);
    this.conversionService = conversionService;
    addLibraryItemUseCase = new AddLibraryItemUseCase(repositoryFactory);
    updateLibraryItemUseCase = new UpdateLibraryItemUseCase(repositoryFactory);
    removeLibraryItemUseCase = new RemoveLibraryItemUseCase(repositoryFactory);
  }

  /**
   * This method saves a library item by applying a provided save method.
   *
   * @param item The library item to be saved.
   * @param saveMethod The method to be used for saving the item.
   * @return The saved item as a DTO.
   */
  private MagazineDto saveItem(LibraryItemDto item, Function<MagazineEntity, LibraryItem> saveMethod) {
    MagazineEntity entity = conversionService.convert(item, MagazineEntity.class);
    LibraryItem savedItem = saveMethod.apply(entity);
    logger.trace("Saved item: {}", savedItem);
    MagazineDto dto = conversionService.convert(savedItem, MagazineDto.class);
    logger.trace("Converted item: {}", dto);
    return dto;
  }

  /**
   * This method adds a new library item.
   *
   * @param item The library item to be added.
   * @return The added item as a DTO.
   */
  @Override
  public MagazineDto addItem(LibraryItemDto item) {
    return saveItem(item, addLibraryItemUseCase::addItem);
  }

  /**
   * This method updates an existing library item.
   *
   * @param item The library item to be updated.
   * @return The updated item as a DTO.
   */
  @Override
  public MagazineDto updateItem(LibraryItemDto item) {
    return saveItem(item, updateLibraryItemUseCase::updateItem);
  }

  /**
   * This method removes an existing library item.
   *
   * @param item The library item to be removed.
   */
  @Override
  public void removeItem(LibraryItemDto item) {
    MagazineEntity entity = conversionService.convert(item, MagazineEntity.class);
    removeLibraryItemUseCase.removeItem(entity);
  }
}