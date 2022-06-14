package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.CategoryDto;
import gosmart.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Category Created Successfully!!",true,categoryDto1));
    }

    @PutMapping("")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryDto));
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryDetailsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted Successfully !!",true,null));
    }
}
