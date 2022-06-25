package gosmart.service.service;

import gosmart.service.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto registerUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto);

    public UserDto getUserDetailsById(String userId);

    public List<UserDto> getAllUsers();

    public void deleteUserById(String userId);

    public UserDto getUserDetailsByEmail(String email);
}
