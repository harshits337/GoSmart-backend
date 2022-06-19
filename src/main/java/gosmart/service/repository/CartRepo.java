package gosmart.service.repository;

import gosmart.service.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo  extends JpaRepository<Cart,String> {
    List<Cart> findByUserId(String userId);
}
