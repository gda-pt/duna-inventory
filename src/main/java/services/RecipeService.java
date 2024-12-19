package services;

import entities.Recipe;
import entities.RecipeItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RecipeService {

    /**
     * Retrieves all recipes.
     *
     * @return A list of all `Recipe` entities.
     */
    public List<Recipe> findAll() {
        return Recipe.listAll();
    }

    /**
     * Finds a recipe by its ID.
     *
     * @param id The ID of the recipe to find.
     * @return An Optional containing the `Recipe` entity, or empty if not found.
     */
    public Optional<Recipe> findById(Long id) {
        return Recipe.findByIdOptional(id);
    }

    /**
     * Creates a new recipe.
     *
     * @param recipe The `Recipe` entity to be persisted.
     */
    @Transactional
    public void createRecipe(Recipe recipe) {
        for (RecipeItem item : recipe.getIngredients()) {
            item.setRecipe(recipe);
        }
        recipe.persist();
    }

    /**
     * Updates an existing recipe.
     *
     * @param recipe The `Recipe` entity containing updated data.
     * @return The updated `Recipe` entity.
     */
    @Transactional
    public Recipe updateRecipe(Recipe recipe) {
        Recipe existingRecipe = Recipe.findById(recipe.id);
        if (existingRecipe == null) {
            throw new IllegalArgumentException("Recipe ID not found: " + recipe.id);
        }

        existingRecipe.setBeerType(recipe.getBeerType());
        // Detach and update ingredients
        existingRecipe.getIngredients().clear();
        existingRecipe.getIngredients().addAll(recipe.getIngredients());
        for (RecipeItem item : recipe.getIngredients()) {
            item.setRecipe(existingRecipe);
        }

        return existingRecipe;
    }

    /**
     * Deletes a recipe by its ID.
     *
     * @param id The ID of the recipe to delete.
     */
    @Transactional
    public void deleteRecipe(Long id) {
        Recipe recipe = Recipe.findById(id);
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe ID not found: " + id);
        }
        recipe.delete();
    }
}