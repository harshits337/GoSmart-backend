package gosmart.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {

    @Id
    private String id;

    @NotEmpty
    private String orderId;

    @NotEmpty
    private String productId;

    private Integer quantity;

    private Integer cost;

    private String createdAt;

    private String updatedAt;
}
