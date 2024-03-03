package frl.hacklab.librarysystem.catalog.application.usecases;

import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the use case for adding a library item.
 * It uses a repository factory to get the appropriate repository for the item to be added.
 */
public class AddLibraryItemUseCase {
  private final Logger logger = LoggerFactory.getLogger(AddLibraryItemUseCase.class);
  private final RepositoryFactory repositoryFactory;

  /**
   * Constructor for the AddLibraryItemUseCase.
   * It initializes the repository factory.
   *
   * @param repositoryFactory The factory for creating repositories.
   */
  public AddLibraryItemUseCase(RepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  /**
   * This method adds a library item.
   * It first logs the item to be added, then gets the appropriate repository for the item using the repository factory,
   * and finally saves the item using the repository.
   *
   * @param item The library item to be added.
   * @return The added item.
   */
  public LibraryItem addItem(LibraryItem item) {
    logger.trace("Adding item: {}", item);
    ItemRepository itemRepository = repositoryFactory.getRepository(item);
    return itemRepository.save(item);
  }
}