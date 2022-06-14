package gosmart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    List<SubCategoryDto> subCategories;

    private String createdAt;

    private String updatedAt;
}
