package gosmart.service.service.impl;

import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Inventory;
import gosmart.service.repository.InventoryRepo;
import gosmart.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;


    @Override
    @Transactional
    public Inventory addInventory(Inventory inventory) {
        inventory.setId(UUID.randomUUID().toString());
        inventory.setCreatedAt(Instant.now().toString());
        return inventoryRepo.save(inventory);
    }

    @Override
    @Transactional
    public Inventory updateInventory(Inventory inventory) {
        Inventory inventory1 = inventoryRepo.findById(inventory.getId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Inventory Id"));
        inventory1.setStockUnits(inventory.getStockUnits());
        inventory1.setUpdatedAt(Instant.now().toString());
        return inventoryRepo.save(inventory1);
    }

    @Override
    public Inventory getInventoryForProductId(String productId) {
        return inventoryRepo.findByProductId(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid Inventory Id"));
    }
}
