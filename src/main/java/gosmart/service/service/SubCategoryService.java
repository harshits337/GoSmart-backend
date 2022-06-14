package gosmart.service.service;

import gosmart.service.dto.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {

    public SubCategoryDto addSubCategory(SubCategoryDto subCategoryDto);

    public SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto);

    public List<SubCategoryDto> getAllSubCategoriesForCategoryId(String categoryId);

    public SubCategoryDto getSubCategoryDetailsById(String subCategoryId);

    public void deleteSubcategoryById(String subcategoryId);
}
