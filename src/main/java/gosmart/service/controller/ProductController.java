package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.ProductDto;
import gosmart.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Product Created Successfully !!",true,productService.addProduct(productDto)));
    }

    @PutMapping("")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductDetailsById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetailsById(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable String id){
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product Deleted!!!",true));
    }
}
