package gosmart.service.service;

import gosmart.service.dto.OrderDto;
import gosmart.service.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    public OrderDto placeOrder(OrderDto orderDto);

    public OrderDto updateOrderStatus(String orderId,OrderStatus orderStatus);

    public OrderDto getOrderDetailsById(String orderId);

    public List<OrderDto> getAllOrders();

}
