package frl.hacklab.librarysystem.catalog.infrastructure.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("WEEKLY")
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class WeeklyMagazineEntity extends MagazineEntity {
}
