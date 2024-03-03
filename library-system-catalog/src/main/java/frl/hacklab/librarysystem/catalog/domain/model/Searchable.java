package frl.hacklab.librarysystem.catalog.domain.model;

public interface Searchable {

  /**
   * @return true if the item matches the search string, false otherwise
   */
  boolean matchesSearch(String search);
}
