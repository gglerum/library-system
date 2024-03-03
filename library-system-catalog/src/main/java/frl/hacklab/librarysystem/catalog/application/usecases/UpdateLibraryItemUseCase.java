package frl.hacklab.librarysystem.catalog.application.usecases;

import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the use case for updating a library item.
 * It uses a repository factory to get the appropriate repository for the item to be updated.
 */
public class UpdateLibraryItemUseCase {
  private final Logger logger = LoggerFactory.getLogger(UpdateLibraryItemUseCase.class);
  private final RepositoryFactory repositoryFactory;

  /**
   * Constructor for the UpdateLibraryItemUseCase.
   * It initializes the repository factory.
   *
   * @param repositoryFactory The factory for creating repositories.
   */
  public UpdateLibraryItemUseCase(RepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  /**
   * This method updates a library item.
   * It first logs the item to be updated, then gets the appropriate repository for the item using the repository factory,
   * and finally saves the item using the repository.
   *
   * @param item The library item to be updated.
   * @return The updated item.
   */
  public LibraryItem updateItem(LibraryItem item) {
    logger.trace("Updating item: {}", item);
    ItemRepository itemRepository = repositoryFactory.getRepository(item);
    return itemRepository.save(item);
  }
}