package gosmart.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
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
