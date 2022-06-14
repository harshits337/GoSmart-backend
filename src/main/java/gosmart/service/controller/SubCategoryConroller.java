package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.SubCategoryDto;
import gosmart.service.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/rest/api/v1/subcategory")
@RestController
public class SubCategoryConroller {

    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createsSubcategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Sub Category Successfully created !!!",true,subCategoryService.addSubCategory(subCategoryDto)));
    }

    @PutMapping("")
    public ResponseEntity<SubCategoryDto> updateSubcategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryService.updateSubCategory(subCategoryDto));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<SubCategoryDto>> getAllSubCategoriesForCategoryId(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryService.getAllSubCategoriesForCategoryId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDto> getSubcategoryDetailsById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryService.getSubCategoryDetailsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSubcategoryById(@PathVariable String id){
        subCategoryService.deleteSubcategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted Successfully",true));
    }
}

