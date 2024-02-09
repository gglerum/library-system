package frl.hacklab.librarysystem.catalog.details.items;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("WEEKLY")
@SuperBuilder
@NoArgsConstructor
public class WeeklyMagazine extends Magazine {
}
