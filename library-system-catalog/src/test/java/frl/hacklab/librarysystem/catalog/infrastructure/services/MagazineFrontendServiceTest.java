package frl.hacklab.librarysystem.catalog.infrastructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.TestLogAppender;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.application.usecases.GetLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.domain.model.LibraryItem;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * This class is used to test the MagazineFrontendService class.
 */
@SpringJUnitConfig(classes = CatalogTestConfig.class)
@RunWith(MockitoJUnitRunner.StrictStubs.class)
class MagazineFrontendServiceTest {
  @Mock
  private GetLibraryItemUseCase useCase;
  @Mock
  private ConversionService conversionService;
  @InjectMocks
  private MagazineFrontendService magazineFrontendService;

  @BeforeEach
  void reset(){
    TestLogAppender.reset();
  }

  @Test
  @DisplayName("Should return MagazineDto when item exists")
  void shouldReturnMagazineDtoWhenItemExists() {
    LibraryItem item = mock(LibraryItem.class);
    MagazineDto expectedDto = new MagazineDto();

    when(useCase.getItem(MagazineDto.class, 1L)).thenReturn(item);
    when(conversionService.convert(item, MagazineDto.class)).thenReturn(expectedDto);

    MagazineDto actualDto = magazineFrontendService.getItem(MagazineDto.class, 1L);

    assertEquals(expectedDto, actualDto);
    assertTrue(TestLogAppender.contains("Retrieved item: " + item));
    assertTrue(TestLogAppender.contains("Converted item: " + expectedDto));
  }

  @Test
  @DisplayName("Should throw exception when item does not exist")
  void shouldThrowExceptionWhenItemDoesNotExist() {
    when(useCase.getItem(MagazineDto.class, 1L)).thenThrow(NoSuchElementException.class);

    Exception exception = assertThrows(NoSuchElementException.class, () -> magazineFrontendService.getItem(MagazineDto.class, 1L));
    assertEquals("MagazineDto with id 1 not found", exception.getMessage());
    assertTrue(TestLogAppender.contains("Item of type MagazineDto not found: 1"));
  }

  @Test
  @DisplayName("Should throw exception when there is no repository for the item")
  void shouldThrowExceptionWhenThereIsNoRepositoryForTheItem() {
    when(useCase.getItem(MagazineDto.class, 1L)).thenThrow(IllegalArgumentException.class);

    assertThrows(IllegalArgumentException.class, () -> magazineFrontendService.getItem(MagazineDto.class, 1L));
  }
}