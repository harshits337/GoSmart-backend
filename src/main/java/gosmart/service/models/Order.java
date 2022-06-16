package gosmart.service.models;

import gosmart.service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @NotEmpty
    private String userId;

    private Integer totalCost;


    private OrderStatus orderStatus;

    private String createdAt;

    private String updatedAt;
}
