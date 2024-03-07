package frl.hacklab.librarysystem.catalog.infrastructure.mappers;

import static org.assertj.core.api.Assertions.*;
import static org.instancio.Select.field;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import frl.hacklab.librarysystem.catalog.CatalogTestConfig;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.application.dto.MagazineType;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.DailyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MonthlyMagazineEntity;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.WeeklyMagazineEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = CatalogTestConfig.class)
class MagazineMapperTest {

  @Autowired
  private ConversionService conversionService;

  @Test
  void testConvertDailyMagazineToDtoIsSuccess(){
    DailyMagazineEntity magazineEntity = Instancio.of(DailyMagazineEntity.class)
        .create();

    MagazineDto magazineDto = conversionService.convert(magazineEntity, MagazineDto.class);

    assertThat(magazineDto.getType()).isEqualTo(MagazineType.DAILY);
  }

  @Test
  void testConvertMagazineEntityToDTOThrowsException(){
    MagazineEntity magazineEntity = Instancio.of(MagazineEntity.class)
        .create();

    //assert that conversion throws java.lang.IllegalArgumentException
    assertThatThrownBy(() -> conversionService.convert(magazineEntity, MagazineDto.class))
        .isInstanceOf(ConversionFailedException.class)
        .hasStackTraceContaining("Invalid magazine type");
  }

  @Test
  void testConvertDtoToDailyMagazineIsSuccess(){
    MagazineDto magazineDto = Instancio.of(MagazineDto.class)
        .set(field(MagazineDto::getType), MagazineType.DAILY)
        .create();

    DailyMagazineEntity magazineEntity = (DailyMagazineEntity) conversionService.convert(magazineDto, MagazineEntity.class);

    assertThat(magazineEntity).isInstanceOf(DailyMagazineEntity.class);
    assertThat(magazineEntity.getIsnn()).isEqualTo(magazineDto.getIsnn());
  }

  @Test
  void testConvertDtoToWeeklyMagazineIsSuccess(){
    MagazineDto magazineDto = Instancio.of(MagazineDto.class)
        .set(field(MagazineDto::getType), MagazineType.WEEKLY)
        .create();

    WeeklyMagazineEntity magazineEntity = (WeeklyMagazineEntity) conversionService.convert(magazineDto, MagazineEntity.class);

    assertThat(magazineEntity).isInstanceOf(WeeklyMagazineEntity.class);
    assertThat(magazineEntity.getIsnn()).isEqualTo(magazineDto.getIsnn());
  }

  @Test
  void testConvertDtoToMonthlyMagazineIsSuccess(){
    MagazineDto magazineDto = Instancio.of(MagazineDto.class)
        .set(field(MagazineDto::getType), MagazineType.MONTHLY)
        .create();

    MonthlyMagazineEntity magazineEntity = (MonthlyMagazineEntity) conversionService.convert(magazineDto, MagazineEntity.class);

    assertThat(magazineEntity).isInstanceOf(MonthlyMagazineEntity.class);
    assertThat(magazineEntity.getIsnn()).isEqualTo(magazineDto.getIsnn());
    assertThat(magazineEntity.getName()).isEqualTo(magazineDto.getName());
  }

  @Test
  void testConvertDtoWithWrongTypeThrowsException(){
    //mock MagazineType so it has the value WRONG
    try(MockedStatic<MagazineType> mockedEnum = mockStatic(MagazineType.class)) {
      MagazineType WRONG = mock(MagazineType.class);
      when(WRONG.ordinal()).thenReturn(3);
      mockedEnum.when(MagazineType::values).thenReturn(new MagazineType[]{ MagazineType.DAILY, MagazineType.MONTHLY, MagazineType.WEEKLY, WRONG });

      MagazineDto magazineDto = Instancio.of(MagazineDto.class)
          .set(field(MagazineDto::getType), WRONG)
          .create();

      assertThatThrownBy(() -> conversionService.convert(magazineDto, MagazineEntity.class))
          .isInstanceOf(ConversionFailedException.class)
          .hasStackTraceContaining("Invalid magazine type");
    }
  }

}