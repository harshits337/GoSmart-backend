package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.CartDto;
import gosmart.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/cart")
public class CartContoller {

    @Autowired
    CartService cartService;

    @PostMapping("")
    public ResponseEntity<CartDto> addToCart(@Valid @RequestBody CartDto cartDto) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(cartDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted Successfully!!",true));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CartDto>> getCartItems(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllItemsInCart(userId));
    }
}
