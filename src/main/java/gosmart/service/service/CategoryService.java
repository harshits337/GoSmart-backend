package gosmart.service.service;

import gosmart.service.dto.CategoryDto;
import gosmart.service.models.Category;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto);

    public CategoryDto getCategoryDetailsById(String categoryId);

    public List<CategoryDto> getAllCategories();

    public void deleteCategory(String categoryId);
}
