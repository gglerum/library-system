package frl.hacklab.librarysystem.catalog.infrastructure.mappers;

import frl.hacklab.librarysystem.catalog.application.dto.MagazineDto;
import frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity.MagazineEntity;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MagazineMapper extends Converter<MagazineEntity, MagazineDto> {
  //MagazineEntity toEntity(MagazineDto dto);
  @Override
  MagazineDto convert(MagazineEntity entity);
}
