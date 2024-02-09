package frl.hacklab.librarysystem.catalog.details.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.core.items.LibraryItem;
import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import frl.hacklab.librarysystem.catalog.details.items.DailyMagazine;
import frl.hacklab.librarysystem.catalog.details.items.Magazine;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@SpringJUnitConfig(classes = CatalogTestConfig.class)
class LibraryItemServiceTest {

  @Autowired
  private LibraryItemService libraryItemService;

  @Autowired
  private MagazineRepository magazineRepository;

  @Test
  void addItemShouldCallUseCase() {
    LibraryItem item = DailyMagazine.builder()
        .title("Test Magazine")
        .isnn("1234-5678")
        .appearanceNumber(12)
        .build();
    Magazine savedItem = (Magazine) libraryItemService.addItem(item);

    Optional<? extends LibraryItem> retrievedItem = magazineRepository.findById(savedItem.getId());
    assertTrue(retrievedItem.isPresent());
    assertEquals(item.getTitle(), retrievedItem.get().getTitle());
  }

  @Test
  void removeItemShouldCallUseCase() {
    LibraryItem item = DailyMagazine.builder()
        .isnn("123-345")
        .title("Test Magazine")
        .build();
    Magazine savedItem = (Magazine) libraryItemService.addItem(item);

    libraryItemService.removeItem(savedItem);

    Optional<? extends LibraryItem> retrievedItem = magazineRepository.findById(savedItem.getId());
    assertTrue(retrievedItem.isEmpty());
  }

  @Test
  void updateItemShouldCallUseCase(){
      LibraryItem item = DailyMagazine.builder()
          .isnn("123-345")
          .title("Test Magazine")
          .build();
      Magazine savedItem = (Magazine) libraryItemService.addItem(item);
      savedItem.setTitle("Updated Title");
      Magazine updatedItem = (Magazine) libraryItemService.updateItem(savedItem);

      Optional<? extends LibraryItem> retrievedItem = magazineRepository.findById(updatedItem.getId());
      assertTrue(retrievedItem.isPresent());
      assertEquals(savedItem.getTitle(), retrievedItem.get().getTitle());
  }

  @Test
  void getItemShouldCallUseCase(){
      LibraryItem item = DailyMagazine.builder()
          .isnn("123-345")
          .title("Test Magazine")
          .build();
      Magazine savedItem = (Magazine) libraryItemService.addItem(item);
      Optional<? extends LibraryItem> retrievedItem = Optional.ofNullable(libraryItemService.getItem(Magazine.class, savedItem.getId()));
      assertTrue(retrievedItem.isPresent());
      assertEquals(savedItem.getTitle(), retrievedItem.get().getTitle());
  }
}