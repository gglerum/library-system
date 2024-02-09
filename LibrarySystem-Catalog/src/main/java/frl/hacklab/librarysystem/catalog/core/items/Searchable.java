package frl.hacklab.librarysystem.catalog.core.items;

public interface Searchable {

  /**
   * @return true if the item matches the search string, false otherwise
   */
  boolean matchesSearch(String search);
}
