package frl.hacklab.librarysystem.catalog.application.usecases;

import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the use case for removing a library item.
 * It uses a repository factory to get the appropriate repository for the item to be removed.
 */
public class RemoveLibraryItemUseCase {
  private final Logger logger = LoggerFactory.getLogger(RemoveLibraryItemUseCase.class);
  private final RepositoryFactory repositoryFactory;

  /**
   * Constructor for the RemoveLibraryItemUseCase.
   * It initializes the repository factory.
   *
   * @param repositoryFactory The factory for creating repositories.
   */
  public RemoveLibraryItemUseCase(RepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  /**
   * This method removes a library item.
   * It first logs the item to be removed, then gets the appropriate repository for the item using the repository factory,
   * and finally deletes the item using the repository.
   *
   * @param entity The library item to be removed.
   */
  public void removeItem(LibraryItem entity) {
    logger.trace("Removing item: {}", entity);
    ItemRepository itemRepository = repositoryFactory.getRepository(entity);
    itemRepository.delete(entity);
  }
}