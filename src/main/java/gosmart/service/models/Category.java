package gosmart.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private String createdAt;

    private String updatedAt;
}
