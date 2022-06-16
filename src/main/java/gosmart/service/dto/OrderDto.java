package gosmart.service.dto;

import gosmart.service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String id;

    private String userId;

    private List<OrderProduct> orderList;

    private OrderStatus orderStatus;

    private String createdAt;

    private String updatedAt;
}
