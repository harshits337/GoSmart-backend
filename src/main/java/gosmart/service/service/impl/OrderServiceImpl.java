package gosmart.service.service.impl;

import gosmart.service.dto.OrderDto;
import gosmart.service.dto.OrderProduct;
import gosmart.service.enums.OrderStatus;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.Order;
import gosmart.service.models.OrderDetails;
import gosmart.service.models.Product;
import gosmart.service.repository.OrderRepo;
import gosmart.service.repository.ProductRepo;
import gosmart.service.repository.UserRepo;
import gosmart.service.service.OrderDetailsService;
import gosmart.service.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailsService orderDetailsService;


    @Override
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = dtoToOrder(orderDto);
        order.setId(UUID.randomUUID().toString());
        List<OrderProduct> orderProducts = orderDto.getOrderList();
        orderDetailsService.addOrderDetails(order.getId(),orderProducts);
        order.setCreatedAt(Instant.now().toString());
        orderRepo.save(order);
        OrderDto savedOrder = orderToDto(order);
        savedOrder.setOrderList(orderDto.getOrderList());
        return savedOrder;
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(String orderId,OrderStatus orderStatus) {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        order.setUpdatedAt(Instant.now().toString());
        return orderToDto(orderRepo.save(order));
    }

    @Override
    public OrderDto getOrderDetailsById(String orderId) {
        Order order = findOrderById(orderId);
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsForOrderId(orderId);
        List<OrderProduct> orderProducts =  orderDetails.stream().map(orderDetails1 -> {
            OrderProduct orderProduct = OrderProduct.builder()
                    .productId(orderDetails1.getProductId())
                    .cost(orderDetails1.getCost())
                    .quantity(orderDetails1.getQuantity())
                    .build();

            return orderProduct;
        }).collect(Collectors.toList());

        OrderDto orderValue = orderToDto(order);
        orderValue.setOrderList(orderProducts);
        return orderValue;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> orderToDto(order)).collect(Collectors.toList());
        return orderDtos;
    }

    Order dtoToOrder(OrderDto orderDto){
        return modelMapper.map(orderDto,Order.class);
    }

    OrderDto orderToDto(Order order){
        return modelMapper.map(order,OrderDto.class);
    }

    public Order findOrderById(String orderId){
        return orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Invalid Order Id"));
    }
}
