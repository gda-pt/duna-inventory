package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductionMaterial extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production; // The production event this material is used in

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial; // The raw material used in this production

    @Column(nullable = false)
    private Double amountUsed; // Amount of raw material used for this production in default unit

    public ProductionMaterial(Production production, RawMaterial rawMaterial, Double amountUsed) {
        this.production = production;
        this.rawMaterial = rawMaterial;
        this.amountUsed = amountUsed;
    }
}