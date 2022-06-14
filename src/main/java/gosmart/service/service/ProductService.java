package gosmart.service.service;

import gosmart.service.dto.ProductDto;
import gosmart.service.models.Product;

import java.util.List;

public interface ProductService {

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto);

    public ProductDto getProductDetailsById(String productId);

    public List<ProductDto> getProductsByCategory(String categoryId);

    public void deleteProductById(String productId);

}
