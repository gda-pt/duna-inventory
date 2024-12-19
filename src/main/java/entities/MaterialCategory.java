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
public class MaterialCategory extends AuditableEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    public MaterialCategory(String name) {
        this.name = name;
    }
}
