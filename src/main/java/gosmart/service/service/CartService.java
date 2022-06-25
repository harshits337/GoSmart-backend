package gosmart.service.service;

import gosmart.service.dto.CartDto;

import java.io.IOException;
import java.util.List;

public interface CartService {

    public CartDto addToCart(CartDto cartDto) throws IOException;

    public List<CartDto> getAllItemsInCart(String userId);

    public void deleteItemFromCart(String id);

    public void clearCart (String userId);
}
