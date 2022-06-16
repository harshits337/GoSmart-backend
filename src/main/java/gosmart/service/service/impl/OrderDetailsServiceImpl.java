package gosmart.service.service.impl;

import gosmart.service.dto.OrderProduct;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Order;
import gosmart.service.models.OrderDetails;
import gosmart.service.repository.OrderDetailsRepo;
import gosmart.service.repository.OrderRepo;
import gosmart.service.service.OrderDetailsService;
import gosmart.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    OrderRepo orderRepo;


    @Override
    public List<OrderDetails> addOrderDetails(String orderId, List<OrderProduct> orderProducts) {
        List<OrderDetails>  orderDetailsList = orderProducts.stream().map(orderProduct -> {
            OrderDetails orderDetails = OrderDetails.builder()
                    .orderId(orderId)
                    .id(UUID.randomUUID().toString())
                    .productId(orderProduct.getProductId())
                    .cost(new Integer(orderProduct.getCost()))
                    .quantity(new Integer(orderProduct.getQuantity()))
                    .createdAt(Instant.now().toString())
                    .build();
            orderDetailsRepo.save(orderDetails);
            return orderDetails;
        }).collect(Collectors.toList());
        return  orderDetailsList;
    }

    @Override
    public List<OrderDetails> getOrderDetailsForOrderId(String orderId) {
        return orderDetailsRepo.findByOrderId(orderId);

    }

    public Order findOrderById(String orderId){
        return orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Invalid Order Id"));
    }
}
