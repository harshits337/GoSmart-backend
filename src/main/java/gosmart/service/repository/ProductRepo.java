package gosmart.service.repository;

import gosmart.service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

    List<Product> findByCategoryId(String categoryId);
}
