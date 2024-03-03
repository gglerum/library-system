package frl.hacklab.librarysystem.catalog.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class MagazineDto extends LibraryItemDto {
  private String copyEditor;
  private String isnn;
  private int appearanceNumber;
  private String name;
}
