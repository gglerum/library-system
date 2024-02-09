package frl.hacklab.librarysystem.catalog.core.items;

public interface RepositoryFactory {
  ItemRepository getRepository(LibraryItem item);

  default ItemRepository getRepository(Class<? extends LibraryItem> itemClass){
    throw new UnsupportedOperationException("Not implemented");
  }
}
