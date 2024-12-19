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
public class RawMaterial extends AuditableEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private MaterialCategory category;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;

    @OneToMany(mappedBy = "rawMaterial", fetch = FetchType.LAZY)
    private List<Batch> batches;

    public RawMaterial(String name, MaterialCategory category, UnitOfMeasure unitOfMeasure) {
        this.name = name;
        this.category = category;
        this.unitOfMeasure = unitOfMeasure;
    }
}
