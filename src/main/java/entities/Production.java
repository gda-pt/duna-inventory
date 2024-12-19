package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Production extends AuditableEntity {

    @Column(nullable = false)
    private String beerType; // Name or type of the beer being produced

    @Column(nullable = false)
    private Double quantityProduced; // Total quantity of beer produced in liters

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "production")
    private List<ProductionMaterial> materialsUsed; // Relationship to raw materials used in this production

    public Production(String beerType, Double quantityProduced) {
        this.beerType = beerType;
        this.quantityProduced = quantityProduced;
    }
}