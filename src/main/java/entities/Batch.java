package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Batch extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Temporal(TemporalType.DATE)
    private Date receivedDate;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(nullable = false)
    private Double quantityReceived;

    @Column(nullable = false)
    private String batchNumber;

    public Batch(RawMaterial rawMaterial, Supplier supplier, Date receivedDate, Date expiryDate, Double quantityReceived, String batchNumber) {
        this.rawMaterial = rawMaterial;
        this.supplier = supplier;
        this.receivedDate = receivedDate;
        this.expiryDate = expiryDate;
        this.quantityReceived = quantityReceived;
        this.batchNumber = batchNumber;
    }
}
