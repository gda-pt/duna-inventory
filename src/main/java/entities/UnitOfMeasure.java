package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasure extends AuditableEntity {

    @Column(nullable = false)
    private String unitName;

    private Double conversionFactor;

    public UnitOfMeasure(String unitName, Double conversionFactor) {
        this.unitName = unitName;
        this.conversionFactor = conversionFactor;
    }
}
