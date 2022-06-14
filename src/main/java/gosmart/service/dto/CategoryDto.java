package gosmart.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private String createdAt;

    private String updatedAt;
}
