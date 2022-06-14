package gosmart.service.service.impl;

import gosmart.service.dto.SubCategoryDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Category;
import gosmart.service.models.SubCategory;
import gosmart.service.repository.CategoryRepo;
import gosmart.service.repository.SubCategoryRepo;
import gosmart.service.service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;


    @Override
    @Transactional
    public SubCategoryDto addSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = dtoToSubcategory(subCategoryDto);
        if(!(checkCategoryId(subCategoryDto.getCategoryId()))){
            throw new ResourceNotFoundException("Invalid Category Id");
        }
        subCategory.setId(UUID.randomUUID().toString());
        subCategory.setCreatedAt(Instant.now().toString());
        return subCategoryToDto(subCategoryRepo.save(subCategory));
    }

    @Override
    @Transactional
    public SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = findSubcategoryById(subCategoryDto.getId());
        if(!(checkCategoryId(subCategoryDto.getCategoryId()))){
            throw new ResourceNotFoundException("Invalid Category Id");
        }
        subCategory.setName(subCategoryDto.getName());
        subCategory.setDescription(subCategoryDto.getDescription());
        subCategory.setUpdatedAt(Instant.now().toString());
        return subCategoryToDto(subCategoryRepo.save(subCategory));
    }

    @Override
    public List<SubCategoryDto> getAllSubCategoriesForCategoryId(String categoryId) {
        List<SubCategory> subCategories = subCategoryRepo.findByCategoryId(categoryId);
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> subCategoryToDto(subCategory)).collect(Collectors.toList());
        return subCategoryDtos;
    }

    @Override
    public SubCategoryDto getSubCategoryDetailsById(String subCategoryId) {
        SubCategory subCategory = findSubcategoryById(subCategoryId);
        return subCategoryToDto(subCategory);
    }

    @Override
    public void deleteSubcategoryById(String subcategoryId) {
        SubCategory subCategory = findSubcategoryById(subcategoryId);
        subCategoryRepo.delete(subCategory);
    }

    SubCategory dtoToSubcategory(SubCategoryDto subCategoryDto){
        return modelMapper.map(subCategoryDto,SubCategory.class);
    }

    SubCategoryDto subCategoryToDto(SubCategory subCategory){
        return modelMapper.map(subCategory,SubCategoryDto.class);
    }

    public SubCategory findSubcategoryById(String subCategoryId){
        return subCategoryRepo.findById(subCategoryId).orElseThrow(()-> new ResourceNotFoundException("Invalid sub category Id !!"));
    }

    public Boolean checkCategoryId(String categoryId){
        return categoryRepo.existsById(categoryId);
    }
}
