package services;

import entities.UnitOfMeasure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UnitOfMeasureService {

    /**
     * Retrieves all units of measure.
     *
     * @return A list of all `UnitOfMeasure` entities.
     */
    public List<UnitOfMeasure> findAll() {
        return UnitOfMeasure.listAll();
    }

    /**
     * Finds a unit of measure by its ID.
     *
     * @param id The ID of the unit of measure to retrieve.
     * @return An Optional containing the `UnitOfMeasure` entity, or empty if not found.
     */
    public Optional<UnitOfMeasure> findById(Long id) {
        return UnitOfMeasure.findByIdOptional(id);
    }

    /**
     * Creates a new unit of measure.
     *
     * @param unitOfMeasure The `UnitOfMeasure` entity to be persisted.
     */
    @Transactional
    public void createUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        unitOfMeasure.persist();
    }

    /**
     * Updates an existing unit of measure.
     *
     * @param unitOfMeasure The `UnitOfMeasure` entity containing updated data.
     * @return The updated `UnitOfMeasure` entity.
     */
    @Transactional
    public UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        UnitOfMeasure existingUnitOfMeasure = UnitOfMeasure.findById(unitOfMeasure.id);
        if (existingUnitOfMeasure == null) {
            throw new IllegalArgumentException("UnitOfMeasure ID not found: " + unitOfMeasure.id);
        }

        existingUnitOfMeasure.setUnitName(unitOfMeasure.getUnitName());
        existingUnitOfMeasure.setConversionFactor(unitOfMeasure.getConversionFactor());

        return existingUnitOfMeasure;
    }

    /**
     * Deletes a unit of measure by its ID.
     *
     * @param id The ID of the unit of measure to delete.
     */
    @Transactional
    public void deleteUnitOfMeasure(Long id) {
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.findById(id);
        if (unitOfMeasure == null) {
            throw new IllegalArgumentException("UnitOfMeasure ID not found: " + id);
        }
        unitOfMeasure.delete();
    }
}