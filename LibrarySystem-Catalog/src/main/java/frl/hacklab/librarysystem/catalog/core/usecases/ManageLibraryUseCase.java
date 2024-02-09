package frl.hacklab.librarysystem.catalog.core.usecases;

import frl.hacklab.librarysystem.catalog.core.items.ItemRepository;
import frl.hacklab.librarysystem.catalog.core.items.LibraryItem;
import frl.hacklab.librarysystem.catalog.core.items.RepositoryFactory;

public class ManageLibraryUseCase {
    private final RepositoryFactory repositoryFactory;

    public ManageLibraryUseCase(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public LibraryItem addItem(LibraryItem item) {
        ItemRepository itemRepository = repositoryFactory.getRepository(item);
        return itemRepository.save(item);
    }

    public void removeItem(LibraryItem item) {
        ItemRepository itemRepository = repositoryFactory.getRepository(item);
        itemRepository.delete(item);
    }

    public LibraryItem updateItem(LibraryItem item) {
        ItemRepository itemRepository = repositoryFactory.getRepository(item);
        return itemRepository.save(item);
    }

    public LibraryItem getItem(Class<? extends LibraryItem> itemClass, Long id) {
        ItemRepository itemRepository = repositoryFactory.getRepository(itemClass);
        return itemRepository.findById(id).orElseThrow();
    }
}
