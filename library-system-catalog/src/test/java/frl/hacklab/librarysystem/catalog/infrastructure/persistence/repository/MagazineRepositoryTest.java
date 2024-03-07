import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import frl.hacklab.librarysystem.catalog.domain.repository.ItemRepository;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.DailyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringJUnitConfig(classes = CatalogTestConfig.class)
@DataJpaTest
public class MagazineRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository magazineJpaRepository;

    private MagazineEntity magazineEntity;

    @BeforeEach
    public void setUp() {
        magazineEntity = new DailyMagazineEntity();
        magazineEntity.setTitle("Test Magazine");
        entityManager.persist(magazineEntity);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnMagazine() {
        Optional<? extends LibraryItem> found = magazineJpaRepository.findById(magazineEntity.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getTitle()).isEqualTo(magazineEntity.getTitle());
    }

    @Test
    public void whenInvalidId_thenReturnEmpty() {
        Optional<? extends LibraryItem> found = magazineJpaRepository.findById(-1L);

        assertThat(found.isPresent()).isFalse();
    }

    @Test
    public void saveMagazineShouldPersistAndReturnMagazine() {
        DailyMagazineEntity magazine = new DailyMagazineEntity();
        magazine.setTitle("Test Magazine");

        LibraryItem savedMagazine = magazineJpaRepository.save(magazine);

        assertThat(savedMagazine).isNotNull();
        assertThat(savedMagazine.getTitle()).isEqualTo(magazine.getTitle());

        // Fetch the magazine directly from the database to ensure it was saved correctly
        DailyMagazineEntity dbMagazine = entityManager.find(DailyMagazineEntity.class, savedMagazine.getId());
        assertThat(dbMagazine).isNotNull();
        assertThat(dbMagazine.getTitle()).isEqualTo(magazine.getTitle());
    }

    @Test
    public void saveShouldHandleNullEntity(){
        assertThatThrownBy(() -> magazineJpaRepository.save(null))
            .isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

    @Test
    public void saveShouldUpdateExistingEntity(){
        magazineEntity.setTitle("Updated Title");
        LibraryItem updatedMagazine = magazineJpaRepository.save(magazineEntity);
        assertThat(updatedMagazine).isNotNull();
        assertThat(updatedMagazine.getTitle()).isEqualTo(magazineEntity.getTitle());

        DailyMagazineEntity dbMagazine = entityManager.find(DailyMagazineEntity.class, magazineEntity.getId());
        assertThat(dbMagazine).isNotNull();
        assertThat(dbMagazine.getTitle()).isEqualTo(updatedMagazine.getTitle());
    }
}