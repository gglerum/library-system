package frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "magazine_type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "magazine")
public class MagazineEntity extends LibraryItemEntity {
  private String copyEditor;
  @NonNull
  private String isnn;
  @NonNull
  private int appearanceNumber;
}
