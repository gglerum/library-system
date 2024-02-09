package frl.hacklab.librarysystem.catalog.details.items;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "magazine_type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
@NoArgsConstructor
public class Magazine extends LibraryItem implements
    frl.hacklab.librarysystem.catalog.core.items.Magazine {
  private String copyEditor;
  @NonNull
  private String isnn;
  @NonNull
  private int appearanceNumber;
}
