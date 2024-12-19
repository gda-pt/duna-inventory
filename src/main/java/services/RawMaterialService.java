package services;

import entities.RawMaterial;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RawMaterialService {

    /**
     * Retrieves all raw materials.
     *
     * @return A list of all `RawMaterial` entities.
     */
    public List<RawMaterial> findAll() {
        return RawMaterial.listAll();
    }

    /**
     * Finds a raw material by its ID.
     *
     * @param id The ID of the raw material to retrieve.
     * @return An Optional containing the `RawMaterial` entity, or empty if not found.
     */
    public Optional<RawMaterial> findById(Long id) {
        return RawMaterial.findByIdOptional(id);
    }

    /**
     * Creates a new raw material.
     *
     * @param rawMaterial The `RawMaterial` entity to persist.
     */
    @Transactional
    public void createRawMaterial(RawMaterial rawMaterial) {
        rawMaterial.persist();
    }

    /**
     * Updates an existing raw material.
     *
     * @param rawMaterial The `RawMaterial` entity containing updated data.
     * @return The updated `RawMaterial` entity.
     */
    @Transactional
    public RawMaterial updateRawMaterial(RawMaterial rawMaterial) {
        RawMaterial existingRawMaterial = RawMaterial.findById(rawMaterial.id);
        if (existingRawMaterial == null) {
            throw new IllegalArgumentException("RawMaterial ID not found: " + rawMaterial.id);
        }

        existingRawMaterial.setName(rawMaterial.getName());
        existingRawMaterial.setDescription(rawMaterial.getDescription());
        existingRawMaterial.setCategory(rawMaterial.getCategory());
        existingRawMaterial.setUnitOfMeasure(rawMaterial.getUnitOfMeasure());

        return existingRawMaterial;
    }

    /**
     * Deletes a raw material by its ID.
     *
     * @param id The ID of the raw material to delete.
     */
    @Transactional
    public void deleteRawMaterial(Long id) {
        RawMaterial rawMaterial = RawMaterial.findById(id);
        if (rawMaterial == null) {
            throw new IllegalArgumentException("RawMaterial ID not found: " + id);
        }
        rawMaterial.delete();
    }
}