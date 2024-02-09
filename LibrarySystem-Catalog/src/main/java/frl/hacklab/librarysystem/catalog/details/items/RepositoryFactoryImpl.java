package frl.hacklab.librarysystem.catalog.details.items;

import frl.hacklab.librarysystem.catalog.core.items.ItemRepository;
import frl.hacklab.librarysystem.catalog.core.items.LibraryItem;
import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import frl.hacklab.librarysystem.catalog.core.items.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactoryImpl implements RepositoryFactory {
  @Autowired
  private MagazineRepository magazineRepository;

  @Override
  public ItemRepository getRepository(LibraryItem item) {
    if (item instanceof Magazine) {
      return magazineRepository;
    }
    throw new IllegalArgumentException("No repository found for item: " + item.getClass().getName());
  }

  @Override
  public ItemRepository getRepository(Class<? extends LibraryItem> itemClass) {
    if (itemClass.equals(Magazine.class)) {
      return magazineRepository;
    }
    throw new IllegalArgumentException("No repository found for item: " + itemClass.getName());
  }
}
