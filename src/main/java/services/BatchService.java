package services;

import entities.Batch;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BatchService {

    /**
     * Retrieves all batches.
     *
     * @return A list of all `Batch` entities.
     */
    public List<Batch> findAll() {
        return Batch.listAll();
    }

    /**
     * Finds a batch by its ID.
     *
     * @param id The ID of the batch to retrieve.
     * @return An Optional containing the found `Batch`, or empty if not found.
     */
    public Optional<Batch> findById(Long id) {
        return Batch.findByIdOptional(id);
    }

    /**
     * Creates a new batch.
     *
     * @param batch The `Batch` entity to persist.
     */
    @Transactional
    public void createBatch(Batch batch) {
        batch.persist();
    }

    /**
     * Updates an existing batch.
     *
     * @param batch The `Batch` entity containing updated data.
     * @return The updated `Batch` entity.
     */
    @Transactional
    public Batch updateBatch(Batch batch) {
        Batch existingBatch = Batch.findById(batch.id);
        if (existingBatch == null) {
            throw new IllegalArgumentException("Batch ID not found: " + batch.id);
        }

        existingBatch.setRawMaterial(batch.getRawMaterial());
        existingBatch.setSupplier(batch.getSupplier());
        existingBatch.setReceivedDate(batch.getReceivedDate());
        existingBatch.setExpiryDate(batch.getExpiryDate());
        existingBatch.setQuantityReceived(batch.getQuantityReceived());
        existingBatch.setBatchNumber(batch.getBatchNumber());

        return existingBatch;
    }

    /**
     * Deletes a batch by its ID.
     *
     * @param id The ID of the batch to delete.
     */
    @Transactional
    public void deleteBatch(Long id) {
        Batch batch = Batch.findById(id);
        if (batch == null) {
            throw new IllegalArgumentException("Batch ID not found: " + id);
        }
        batch.delete();
    }
}