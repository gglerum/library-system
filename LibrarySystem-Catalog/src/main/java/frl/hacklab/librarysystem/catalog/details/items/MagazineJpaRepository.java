package frl.hacklab.librarysystem.catalog.details.items;

import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import org.springframework.data.repository.CrudRepository;

public interface MagazineJpaRepository extends CrudRepository<Magazine, Long>, MagazineRepository {

}
