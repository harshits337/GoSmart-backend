package gosmart.service.dto;

import gosmart.service.models.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.InputStream;

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
    private String brand;

    @NotEmpty
    private String name;

    @NotEmpty
    private String details;

    @NotEmpty
    private String price;

    private byte[] image;

    private Inventory inventory;

    private String createdAt;

    private String updatedAt;

}
