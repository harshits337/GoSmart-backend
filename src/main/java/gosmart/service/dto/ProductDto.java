package gosmart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String subCategoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String details;

    @NotEmpty
    private String price;

    private String createdAt;

    private String updatedAt;

}
