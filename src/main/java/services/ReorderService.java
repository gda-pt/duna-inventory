package services;

import entities.ReorderLevel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReorderService {

    /**
     * Retrieves all reorder levels.
     *
     * @return A list of all `ReorderLevel` entities.
     */
    public List<ReorderLevel> findAll() {
        return ReorderLevel.listAll();
    }

    /**
     * Finds a reorder level by its ID.
     *
     * @param id The ID of the reorder level to retrieve.
     * @return An Optional containing the `ReorderLevel` entity, or empty if not found.
     */
    public Optional<ReorderLevel> findById(Long id) {
        return ReorderLevel.findByIdOptional(id);
    }

    /**
     * Creates a new reorder level.
     *
     * @param reorderLevel The `ReorderLevel` entity to persist.
     */
    @Transactional
    public void createReorderLevel(ReorderLevel reorderLevel) {
        reorderLevel.persist();
    }

    /**
     * Updates an existing reorder level.
     *
     * @param reorderLevel The `ReorderLevel` entity containing updated data.
     * @return The updated `ReorderLevel` entity.
     */
    @Transactional
    public ReorderLevel updateReorderLevel(ReorderLevel reorderLevel) {
        ReorderLevel existingReorderLevel = ReorderLevel.findById(reorderLevel.id);
        if (existingReorderLevel == null) {
            throw new IllegalArgumentException("ReorderLevel ID not found: " + reorderLevel.id);
        }

        existingReorderLevel.setRawMaterial(reorderLevel.getRawMaterial());
        existingReorderLevel.setMinimumStockLevel(reorderLevel.getMinimumStockLevel());
        existingReorderLevel.setReorderQuantity(reorderLevel.getReorderQuantity());

        return existingReorderLevel;
    }

    /**
     * Deletes a reorder level by its ID.
     *
     * @param id The ID of the reorder level to delete.
     */
    @Transactional
    public void deleteReorderLevel(Long id) {
        ReorderLevel reorderLevel = ReorderLevel.findById(id);
        if (reorderLevel == null) {
            throw new IllegalArgumentException("ReorderLevel ID not found: " + id);
        }
        reorderLevel.delete();
    }
}