package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecipeItem extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe; // Recipe this item belongs to

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial; // The raw material used in this recipe

    @Column(nullable = false)
    private Double amountRequired; // Amount of raw material required for the recipe

    public RecipeItem(Recipe recipe, RawMaterial rawMaterial, Double amountRequired) {
        this.recipe = recipe;
        this.rawMaterial = rawMaterial;
        this.amountRequired = amountRequired;
    }
}