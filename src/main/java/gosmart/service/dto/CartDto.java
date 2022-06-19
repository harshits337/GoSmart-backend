package gosmart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String id;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String productId;

    private Integer quantity;

    private ProductDto productDetails;

    private String createdAt;

    private String updatedAt;
}
