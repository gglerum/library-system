package frl.hacklab.librarysystem.catalog.details.items;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.core.items.BorrowingStatus;
import frl.hacklab.librarysystem.catalog.core.items.Magazine;
import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import java.util.List;
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
    private MagazineRepository magazineRepository;

    @BeforeEach
    public void setup() {
        entityManager.clear();
    }

    @Test
    public void saveMagazineShouldReturnSavedMagazine() {
        Magazine magazine = DailyMagazine.builder()
            .title("Test Magazine")
            .isnn("1234-5678")
            .build();

        Magazine savedMagazine = magazineRepository.save(magazine);

        assertThat(savedMagazine).isNotNull();
        assertThat(savedMagazine.getTitle()).isEqualTo(magazine.getTitle());
    }

    @Test
    public void findMagazineByIdShouldReturnMagazine() {
        Magazine magazine = WeeklyMagazine.builder()
            .isnn("1234-5678")
            .title("Test Magazine")
            .build();
        entityManager.persist(magazine);

        Magazine foundMagazine = magazineRepository.findById(magazine.getId()).orElse(null);

        assertThat(foundMagazine).isNotNull();
        assertThat(foundMagazine.getTitle()).isEqualTo(magazine.getTitle());
    }

    @Test
    public void deleteMagazineShouldRemoveMagazine() {
        Magazine magazine = MonthlyMagazine.builder()
            .isnn("1234-5678")
            .title("Test Magazine")
            .name("December Number")
            .build();
        entityManager.persist(magazine);

        magazineRepository.delete(magazine);
        Magazine foundMagazine = magazineRepository.findById(magazine.getId()).orElse(null);

        assertThat(foundMagazine).isNull();
    }

    @Test
    public void findAllMagazinesShouldReturnAllMagazines() {
        Magazine magazine1 = DailyMagazine.builder()
            .isnn("1234-5678")
            .title("Test Magazine 1")
            .genre("Romance")
            .isOnLoan(BorrowingStatus.AVAILABLE)
            .build();
        entityManager.persist(magazine1);

        Magazine magazine2 = WeeklyMagazine.builder()
            .isnn("1234-5678")
            .title("Test Magazine 2")
            .build();
        entityManager.persist(magazine2);

        List<? extends Magazine> foundMagazines = (List<? extends Magazine>) magazineRepository.findAll();

        assertThat(foundMagazines).hasSize(2);
        assertThat(foundMagazines).extracting(Magazine::getTitle).containsExactlyInAnyOrder(magazine1.getTitle(), magazine2.getTitle());
    }
}