package gosmart.service.service.impl;

import gosmart.service.dto.CategoryDto;
import gosmart.service.dto.SubCategoryDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Category;
import gosmart.service.models.SubCategory;
import gosmart.service.repository.CategoryRepo;
import gosmart.service.repository.SubCategoryRepo;
import gosmart.service.service.CategoryService;
import gosmart.service.service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.ReadOnlyFileSystemException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SubCategoryService subCategoryService;

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        category.setId(UUID.randomUUID().toString());
        category.setCreatedAt(Instant.now().toString());
        return categoryToDto(categoryRepo.save(category));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = findCatById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setUpdatedAt(Instant.now().toString());
        return categoryToDto(categoryRepo.save(category));
    }

    @Override
    public CategoryDto getCategoryDetailsById(String categoryId) {
        Category category = findCatById(categoryId);
        CategoryDto categoryDto = categoryToDto(category);
        categoryDto.setSubCategories(subCategoryService.getAllSubCategoriesForCategoryId(categoryId));
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> {
            CategoryDto categoryDto = categoryToDto(category);
            categoryDto.setSubCategories(subCategoryService.getAllSubCategoriesForCategoryId(categoryDto.getId()));
            return categoryDto;
        }).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = findCatById(categoryId);
        categoryRepo.delete(category);
    }

    Category dtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

    CategoryDto categoryToDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    public Category findCatById(String categoryId){
        return categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Invalid Category Id"));
    }
}
