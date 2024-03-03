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
@DiscriminatorValue("DAILY")
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class DailyMagazineEntity extends MagazineEntity {
}
