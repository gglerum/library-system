package frl.hacklab.librarysystem.catalog.infrastructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.domain.model.BorrowingStatus;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.DailyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository.RepositoryFactoryImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@SpringJUnitConfig(classes = CatalogTestConfig.class)
class MagazineFrontendServiceTest {
  @Autowired
  private MagazineFrontendService libraryItemService;

  @Autowired
  private RepositoryFactoryImpl repositoryFactory;

  @Test
  void getItemShouldCallUseCase(){
      DailyMagazineEntity item = DailyMagazineEntity.builder()
          .isnn("123-345")
          .title("Test Magazine")
          .appearanceNumber(1)
          .copyEditor("Test Editor")
          .genre("Test Genre")
          .isOnLoan(BorrowingStatus.AVAILABLE)
          .ageRating("16+")
          .publicationDate(LocalDate.of(2021, 1, 1))
          .publisher("Test Publisher")
          .build();

      ItemRepository itemRepository = repositoryFactory.getRepository(item);
      MagazineEntity savedItem = (MagazineEntity) itemRepository.save(item);

      Optional<MagazineDto> retrievedItem = Optional.ofNullable(libraryItemService.getItem(
          MagazineDto.class, savedItem.getId()));

      assertTrue(retrievedItem.isPresent());
      assertEquals(savedItem.getIsnn(), retrievedItem.get().getIsnn());
  }
}