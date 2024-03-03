package frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository;

import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import org.springframework.data.repository.CrudRepository;

public interface MagazineJpaRepository extends CrudRepository<MagazineEntity, Long>,
    ItemRepository {

}
