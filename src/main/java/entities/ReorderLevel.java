package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReorderLevel extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;

    @Column(nullable = false)
    private Double minimumStockLevel;

    @Column(nullable = false)
    private Double reorderQuantity;

    public ReorderLevel(RawMaterial rawMaterial, Double minimumStockLevel, Double reorderQuantity) {
        this.rawMaterial = rawMaterial;
        this.minimumStockLevel = minimumStockLevel;
        this.reorderQuantity = reorderQuantity;
    }
}
