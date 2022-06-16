package gosmart.service.service;

import gosmart.service.models.Inventory;
import org.springframework.data.relational.core.sql.In;

public interface InventoryService {

    public Inventory addInventory(Inventory inventory);

    public Inventory updateInventory(Inventory inventory);

    public Inventory getInventoryForProductId(String productId);
}
