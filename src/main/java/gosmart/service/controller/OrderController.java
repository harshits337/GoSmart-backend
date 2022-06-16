package gosmart.service.controller;

import gosmart.service.dto.OrderDto;
import gosmart.service.enums.OrderStatus;
import gosmart.service.models.Order;
import gosmart.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public ResponseEntity<OrderDto> placeOrder(@Valid @RequestBody OrderDto orderDto){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(orderDto));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable String orderId, @RequestParam String status){
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatus(orderId,orderStatus));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderDetails(@PathVariable String orderId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDetailsById(orderId));
    }

    @GetMapping("/all")
    private ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }
}
