package gosmart.service.service.impl;

import gosmart.service.dto.CartDto;
import gosmart.service.dto.ProductDto;
import gosmart.service.dto.UserDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Cart;
import gosmart.service.models.Product;
import gosmart.service.models.User;
import gosmart.service.repository.CartRepo;
import gosmart.service.repository.ProductRepo;
import gosmart.service.repository.UserRepo;
import gosmart.service.service.CartService;
import gosmart.service.service.ProductService;
import gosmart.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public CartDto addToCart(CartDto cartDto) throws IOException {
        ProductDto product = productService.getProductDetailsById(cartDto.getProductId());
        UserDto user = userService.getUserDetailsById(cartDto.getUserId());
        System.out.println(product);
        Cart cart = dtoToCart(cartDto);
        System.out.println(cart);
        cart.setId(UUID.randomUUID().toString());
        cart.setCreatedAt(Instant.now().toString());
        CartDto savedDto = CartToDto(cartRepo.save(cart));
        savedDto.setProductDetails(product);
        System.out.println(savedDto);
        return savedDto;
    }

    @Override
    public List<CartDto> getAllItemsInCart(String userId) {
        List<Cart> cartItem  = cartRepo.findByUserId(userId);
        List<CartDto> cartDtoItem = cartItem.stream().map(cart -> {
            CartDto cartDto = CartToDto(cart);
            try {
                cartDto.setProductDetails(productService.getProductDetailsById(cart.getProductId()));
            } catch (IOException e) {
            }
            return cartDto;
        }).collect(Collectors.toList());
        return cartDtoItem;
    }

    @Override
    public void deleteItemFromCart(String id) {
        Cart cart = cartRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid Cart item"));
        cartRepo.delete(cart);

    }

    @Override
    public void clearCart(String userId) {
        cartRepo.deleteAllByUserId(userId);
    }

    public Cart dtoToCart(CartDto cartDto){
        return modelMapper.map(cartDto,Cart.class);
    }

    public CartDto CartToDto(Cart cart){
        return modelMapper.map(cart,CartDto.class);
    }
}
