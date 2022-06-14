package gosmart.service.repository;

import gosmart.service.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,String> {

    List<SubCategory> findByCategoryId(String categoryId);
}
