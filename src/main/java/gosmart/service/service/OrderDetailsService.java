package gosmart.service.service;

import gosmart.service.dto.OrderDto;
import gosmart.service.dto.OrderProduct;
import gosmart.service.models.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    List<OrderDetails> addOrderDetails(String orderId, List<OrderProduct> orderProducts) ;

    List<OrderDetails> getOrderDetailsForOrderId(String orderId);
}
