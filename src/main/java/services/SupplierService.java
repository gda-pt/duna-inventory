package services;

import entities.Supplier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SupplierService {

    /**
     * Retrieves all suppliers.
     *
     * @return A list of all `Supplier` entities.
     */
    public List<Supplier> findAll() {
        return Supplier.listAll();
    }

    /**
     * Finds a supplier by its ID.
     *
     * @param id The ID of the supplier to retrieve.
     * @return An Optional containing the `Supplier` entity, or empty if not found.
     */
    public Optional<Supplier> findById(Long id) {
        return Supplier.findByIdOptional(id);
    }

    /**
     * Creates a new supplier.
     *
     * @param supplier The `Supplier` entity to be persisted.
     */
    @Transactional
    public void createSupplier(Supplier supplier) {
        supplier.persist();
    }

    /**
     * Updates an existing supplier.
     *
     * @param supplier The `Supplier` entity containing updated data.
     * @return The updated `Supplier` entity.
     */
    @Transactional
    public Supplier updateSupplier(Supplier supplier) {
        Supplier existingSupplier = Supplier.findById(supplier.id);
        if (existingSupplier == null) {
            throw new IllegalArgumentException("Supplier ID not found: " + supplier.id);
        }

        existingSupplier.setName(supplier.getName());
        existingSupplier.setContactName(supplier.getContactName());
        existingSupplier.setPhone(supplier.getPhone());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setAddress(supplier.getAddress());

        return existingSupplier;
    }

    /**
     * Deletes a supplier by its ID.
     *
     * @param id The ID of the supplier to delete.
     */
    @Transactional
    public void deleteSupplier(Long id) {
        Supplier supplier = Supplier.findById(id);
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier ID not found: " + id);
        }
        supplier.delete();
    }
}