package gosmart.service.repository;

import gosmart.service.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,String> {
    List<OrderDetails> findByOrderId(String orderId);
}
