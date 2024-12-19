package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity extends PanacheEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Automatically set on creation

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Set/Updated on modifications

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt; // Soft-delete support

    // Optional: Track user/system that performed the action
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    // Optional method to perform soft-delete
    public void delete(String user) {
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = user;
    }

    // Call this when updating the entity
    public void update(String user) {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = user;
    }
}