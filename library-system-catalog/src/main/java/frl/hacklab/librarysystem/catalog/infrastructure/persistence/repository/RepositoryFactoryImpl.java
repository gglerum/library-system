package frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository;

import frl.hacklab.librarysystem.catalog.application.dto.LibraryItemDto;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactoryImpl implements RepositoryFactory {
  @Autowired
  private MagazineJpaRepository magazineRepository;

  @Override
  public ItemRepository getRepository(LibraryItem item) {
    if (item instanceof MagazineEntity) {
      return magazineRepository;
    }
    throw new IllegalArgumentException("No repository found for item: " + item.getClass().getName());
  }

  @Override
  public ItemRepository getRepository(Class<? extends LibraryItemDto> itemClass) {
    if (itemClass.equals(MagazineDto.class)) {
      return magazineRepository;
    }
    throw new IllegalArgumentException("No repository found for item: " + itemClass.getName());
  }
}
