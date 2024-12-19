package services;

import entities.Production;
import entities.ProductionMaterial;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductionService {

    /**
     * Retrieves all production events.
     *
     * @return A list of all `Production` entities.
     */
    public List<Production> findAll() {
        return Production.listAll();
    }

    /**
     * Finds a production event by its ID.
     *
     * @param id The ID of the production.
     * @return An Optional containing the `Production` entity, or empty if not found.
     */
    public Optional<Production> findById(Long id) {
        return Production.findByIdOptional(id);
    }

    /**
     * Creates a new production event.
     *
     * @param production The `Production` entity to be persisted.
     */
    @Transactional
    public void createProduction(Production production) {
        for (ProductionMaterial material : production.getMaterialsUsed()) {
            material.setProduction(production);
        }
        production.persist();
    }

    /**
     * Updates an existing production event.
     *
     * @param production The `Production` entity with updated details.
     * @return The updated `Production` entity.
     */
    @Transactional
    public Production updateProduction(Production production) {
        Production existingProduction = Production.findById(production.id);
        if (existingProduction == null) {
            throw new IllegalArgumentException("Production ID not found: " + production.id);
        }

        existingProduction.setBeerType(production.getBeerType());
        existingProduction.setQuantityProduced(production.getQuantityProduced());
        // Detach and update materials
        existingProduction.getMaterialsUsed().clear();
        existingProduction.getMaterialsUsed().addAll(production.getMaterialsUsed());
        for (ProductionMaterial material : production.getMaterialsUsed()) {
            material.setProduction(existingProduction);
        }

        return existingProduction;
    }

    /**
     * Deletes a production by its ID.
     *
     * @param id The ID of the production.
     */
    @Transactional
    public void deleteProduction(Long id) {
        Production production = Production.findById(id);
        if (production == null) {
            throw new IllegalArgumentException("Production ID not found: " + id);
        }
        production.delete();
    }
}