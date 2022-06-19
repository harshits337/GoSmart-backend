package gosmart.service.service;

import gosmart.service.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto);

    public void addImageToProduct(MultipartFile file, String productId) throws IOException;

    public ProductDto getProductDetailsById(String productId) throws IOException;

    public List<ProductDto> getProductsByCategory(String categoryId);

    public void deleteProductById(String productId);

}
