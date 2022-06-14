package gosmart.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SubCategory {

    @Id
    private String id;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private String createdAt;

    private String updatedAt;

}
