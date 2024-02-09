package frl.hacklab.librarysystem.catalog.core.items;

import java.util.Optional;

public interface MagazineRepository extends ItemRepository {
  Magazine save(Magazine magazine);
  @Override
  Optional<? extends Magazine> findById(Long id);
  void delete(Magazine magazine);
  Iterable<? extends Magazine> findAll();
}
