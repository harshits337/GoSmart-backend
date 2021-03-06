package gosmart.service.service.impl;

import gosmart.service.dto.ProductDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Inventory;
import gosmart.service.models.Product;
import gosmart.service.repository.CategoryRepo;
import gosmart.service.repository.ProductRepo;
import gosmart.service.repository.SubCategoryRepo;
import gosmart.service.service.CategoryService;
import gosmart.service.service.InventoryService;
import gosmart.service.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    InventoryService inventoryService;

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        Product product = dtoToProduct(productDto);
        if( !(checkSubCategoryId(productDto.getSubCategoryId())) || !(checkCategoryId(productDto.getCategoryId()))){
            throw new ResourceNotFoundException("Invalid Request");
        }

        product.setId(UUID.randomUUID().toString());
        product.setCreatedAt(Instant.now().toString());
        productRepo.save(product);
        Inventory inventory = Inventory.builder()
                .productId(product.getId())
                .stockUnits(5)
                .build();
        inventoryService.addInventory(inventory);
        ProductDto savedProductDto = productToDto(product);
        savedProductDto.setInventory(inventory);
        return savedProductDto;
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = findProductById(productDto.getId());
        product.setName(productDto.getName());
        product.setDetails(productDto.getDetails());
        product.setUpdatedAt(Instant.now().toString());
        productRepo.save(product);
        return productToDto(product);
    }

    @Override
    public ProductDto getProductDetailsById(String productId) {
        Product product = findProductById(productId);
        ProductDto productDto = productToDto(product);
        productDto.setInventory(inventoryService.getInventoryForProductId(productId));
        return productDto;
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        List<ProductDto> productDtoList = products.stream().map(product -> {
            ProductDto productDto = productToDto(product);
            System.out.println(productDto.toString());
            productDto.setInventory(inventoryService.getInventoryForProductId(product.getId()));
            return productDto;
        }).collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public void deleteProductById(String productId) {
        Product product = findProductById(productId);
        productRepo.delete(product);
    }

    Product dtoToProduct(ProductDto productDto){
        return modelMapper.map(productDto,Product.class);
    }

    ProductDto productToDto(Product product){
        return modelMapper.map(product,ProductDto.class);
    }

    public Product findProductById(String productId){
        return productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid Product Id!!"));
    }

    public Boolean checkCategoryId(String categoryId){
        return categoryRepo.existsById(categoryId);
    }

    public Boolean checkSubCategoryId(String subcategoryId) { return subCategoryRepo.existsById(subcategoryId); }
}
