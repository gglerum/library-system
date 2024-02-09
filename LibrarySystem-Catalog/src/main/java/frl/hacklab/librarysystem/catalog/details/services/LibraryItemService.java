package frl.hacklab.librarysystem.catalog.details.services;

import frl.hacklab.librarysystem.catalog.core.items.LibraryItem;
import frl.hacklab.librarysystem.catalog.core.items.RepositoryFactory;
import frl.hacklab.librarysystem.catalog.core.usecases.ManageLibraryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryItemService {
  private final ManageLibraryUseCase useCase;

  @Autowired
  public LibraryItemService(RepositoryFactory repositoryFactory) {
    useCase = new ManageLibraryUseCase(repositoryFactory);
  }

  public LibraryItem addItem(LibraryItem item){
    return useCase.addItem(item);
  }

  public LibraryItem updateItem(LibraryItem item){
    return useCase.updateItem(item);
  }

  public void removeItem(LibraryItem item){
    useCase.removeItem(item);
  }

  public LibraryItem getItem(Class<? extends LibraryItem> itemClass, Long id){
    return useCase.getItem(itemClass, id);
  }
}
