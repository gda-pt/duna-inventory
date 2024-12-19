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
public class Recipe extends AuditableEntity {

    @Column(nullable = false, unique = true)
    private String beerType; // Type of beer this recipe is meant for (e.g., Lager, IPA)

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<RecipeItem> ingredients; // List of ingredients (raw materials) for this recipe

    public Recipe(String beerType) {
        this.beerType = beerType;
    }
}