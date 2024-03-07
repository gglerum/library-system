package frl.hacklab.librarysystem.catalog.infrastructure.mappers;

import frl.hacklab.librarysystem.catalog.application.dto.*;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.extensions.spring.DelegatingConverter;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MagazineMapper extends Converter<MagazineEntity, MagazineDto> {
  //MagazineEntity toEntity(MagazineDto dto);
  @Override
  @Mapping(target = "type", expression = "java(mapName(entity))")
  MagazineDto convert(MagazineEntity entity);

  /**
   * Sets the type of the magazine based on the entity type.
   * @param entity
   * @return type of the magazine
   * @throws IllegalArgumentException if the entity type is invalid
   */
  default MagazineType mapName(MagazineEntity entity) {
    return switch (entity) {
      case DailyMagazineEntity dm -> MagazineType.DAILY;
      case WeeklyMagazineEntity wm -> MagazineType.WEEKLY;
      case MonthlyMagazineEntity mm -> MagazineType.MONTHLY;
      default -> throw new IllegalArgumentException("Invalid magazine type");
    };
  }

  @InheritInverseConfiguration
  @DelegatingConverter
  default MagazineEntity invertConvert(MagazineDto magazineDto) {
    return switch (magazineDto.getType()) {
      case DAILY -> convertToDaily(magazineDto);
      case WEEKLY -> convertToWeekly(magazineDto);
      case MONTHLY -> convertToMonthly(magazineDto);
      default -> throw new IllegalArgumentException("Invalid magazine type");
    };
  }

  DailyMagazineEntity convertToDaily(MagazineDto dto);
  WeeklyMagazineEntity convertToWeekly(MagazineDto dto);
  MonthlyMagazineEntity convertToMonthly(MagazineDto magazineDto);
}
