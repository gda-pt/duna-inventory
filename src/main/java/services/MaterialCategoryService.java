package services;

import entities.MaterialCategory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MaterialCategoryService {

    /**
     * Create a new MaterialCategory
     *
     * @param rawMaterialCategory Name and Description for the category.
     * @param currentUser         User creating the category (for auditing purposes).
     * @return The created MaterialCategory entity.
     */
    @Transactional
    public MaterialCategory createMaterialCategory(MaterialCategory rawMaterialCategory, String currentUser) {
        // Set auditing fields
        rawMaterialCategory.setCreatedBy(currentUser);
        rawMaterialCategory.persist();
        return rawMaterialCategory;
    }

    /**
     * Get all RawMaterialCategories
     *
     * @return List of all MaterialCategory entities.
     */
    public List<MaterialCategory> getAllRawMaterialCategories() {
        return MaterialCategory.listAll();
    }

    /**
     * Get a MaterialCategory by ID
     *
     * @param id MaterialCategory ID.
     * @return The MaterialCategory if found, otherwise an empty Optional.
     */
    public Optional<MaterialCategory> getMaterialCategoryById(Long id) {
        return MaterialCategory.findByIdOptional(id);
    }

    /**
     * Update an existing MaterialCategory
     *
     * @param id              The ID of the category to update.
     * @param updatedCategory The updated MaterialCategory object.
     * @param currentUser     The user performing the update (for auditing purposes).
     * @return The updated MaterialCategory.
     * @throws Exception If the category is not found.
     */
    @Transactional
    public MaterialCategory updateMaterialCategory(Long id, MaterialCategory updatedCategory, String currentUser)
            throws Exception {
        Optional<MaterialCategory> existingCategoryOpt = MaterialCategory.findByIdOptional(id);

        if (existingCategoryOpt.isEmpty()) {
            throw new Exception("MaterialCategory with ID " + id + " not found.");
        }

        MaterialCategory existingCategory = existingCategoryOpt.get();
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        existingCategory.persist();
        return existingCategory;
    }

    /**
     * Delete a MaterialCategory by ID
     *
     * @param id The ID of the category to delete.
     * @throws Exception If the category is not found.
     */
    @Transactional
    public void deleteMaterialCategory(Long id) throws Exception {
        Optional<MaterialCategory> categoryOpt = MaterialCategory.findByIdOptional(id);

        if (categoryOpt.isEmpty()) {
            throw new Exception("MaterialCategory with ID " + id + " not found.");
        }

        MaterialCategory category = categoryOpt.get();
        category.delete();
    }
}