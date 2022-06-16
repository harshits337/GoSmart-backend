package gosmart.service.dto;

import gosmart.service.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10,max = 10)
    private String phoneNumber;

    private String addressId;

    private UserType userType;

    @NotEmpty
    private String password;

    private String createdAt;

    private String updatedAt;
}
