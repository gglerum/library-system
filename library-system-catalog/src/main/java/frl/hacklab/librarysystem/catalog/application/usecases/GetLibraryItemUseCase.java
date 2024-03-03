package frl.hacklab.librarysystem.catalog.application.usecases;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the use case for retrieving a library item.
 * It uses a repository factory to get the appropriate repository for the item to be retrieved.
 */
public class GetLibraryItemUseCase {
    private final Logger logger = LoggerFactory.getLogger(GetLibraryItemUseCase.class);
    private final RepositoryFactory repositoryFactory;

    /**
     * Constructor for the GetLibraryItemUseCase.
     * It initializes the repository factory.
     *
     * @param repositoryFactory The factory for creating repositories.
     */
    public GetLibraryItemUseCase(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    /**
     * This method retrieves a library item.
     * It first logs the item to be retrieved, then gets the appropriate repository for the item using the repository factory,
     * and finally retrieves the item using the repository.
     *
     * @param itemClass The class of the library item to be retrieved.
     * @param id The id of the library item to be retrieved.
     * @return The retrieved item.
     */
    public LibraryItem getItem(Class<? extends LibraryItemDto> itemClass, Long id) {
        logger.trace("attempting to retrieve item ({}, {})", itemClass, id);
        ItemRepository itemRepository = repositoryFactory.getRepository(itemClass);
        return itemRepository.findById(id).orElseThrow();
    }
}