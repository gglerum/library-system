package frl.hacklab.librarysystem.catalog.infrastructure.persistence.repository;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.domain.model.BorrowingStatus;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.DailyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MonthlyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.WeeklyMagazineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@SpringJUnitConfig(classes = CatalogTestConfig.class)
public class MagazineRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RepositoryFactoryImpl repositoryFactory;

    @BeforeEach
    public void setup() {
        entityManager.clear();
    }

    @Test
    public void saveMagazineShouldReturnSavedMagazine() {
        DailyMagazineEntity magazine = DailyMagazineEntity.builder()
            .title("Test Magazine")
            .isnn("1234-5678")
            .build();

        ItemRepository itemRepository = repositoryFactory.getRepository(magazine);
        LibraryItem savedMagazine = itemRepository.save(magazine);

        assertThat(savedMagazine).isNotNull();
        assertThat(savedMagazine.getTitle()).isEqualTo(magazine.getTitle());
    }

    @Test
    public void findMagazineByIdShouldReturnMagazine() {
        WeeklyMagazineEntity magazine = WeeklyMagazineEntity.builder()
            .isnn("1234-5678")
            .title("Test Magazine")
            .build();
        entityManager.persist(magazine);

        ItemRepository itemRepository = repositoryFactory.getRepository(MagazineDto.class);
        LibraryItem foundMagazine = itemRepository.findById(magazine.getId()).orElse(null);

        assertThat(foundMagazine).isNotNull();
        assertThat(foundMagazine.getTitle()).isEqualTo(magazine.getTitle());
    }

    @Test
    public void deleteMagazineShouldRemoveMagazine() {
        MonthlyMagazineEntity magazine = MonthlyMagazineEntity.builder()
            .isnn("1234-5678")
            .title("Test Magazine")
            .name("December Number")
            .build();
        entityManager.persist(magazine);

        ItemRepository itemRepository = repositoryFactory.getRepository(magazine);
        itemRepository.delete(magazine);

        LibraryItem foundMagazine = itemRepository.findById(magazine.getId()).orElse(null);

        assertThat(foundMagazine).isNull();
    }

    @Test
    public void findAllMagazinesShouldReturnAllMagazines() {
        DailyMagazineEntity magazine1 = DailyMagazineEntity.builder()
            .isnn("1234-5678")
            .title("Test Magazine 1")
            .genre("Romance")
            .isOnLoan(BorrowingStatus.AVAILABLE)
            .build();
        entityManager.persist(magazine1);

        WeeklyMagazineEntity magazine2 = WeeklyMagazineEntity.builder()
            .isnn("1234-5678")
            .title("Test Magazine 2")
            .build();
        entityManager.persist(magazine2);
        ItemRepository magazineRepository = repositoryFactory.getRepository(MagazineDto.class);
        Iterable<? extends LibraryItem> foundMagazines = magazineRepository.findAll();

        assertThat(foundMagazines).hasSize(2);
        assertThat(foundMagazines).extracting(LibraryItem::getTitle).containsExactlyInAnyOrder(magazine1.getTitle(), magazine2.getTitle());
    }
}