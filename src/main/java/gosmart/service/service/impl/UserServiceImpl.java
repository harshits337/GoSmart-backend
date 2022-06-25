package gosmart.service.service.impl;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.UserDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.models.User;
import gosmart.service.repository.UserRepo;
import gosmart.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto registerUser(UserDto userDto) {
        if(findUserByEmail(userDto.getEmail())){
            throw new ResourceNotFoundException("Email Already Present!!!");
        }

        User user = dtoToUser(userDto);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID().toString());
        user.setCreatedAt(Instant.now().toString());
        return UserToDto(userRepo.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = findUserById(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUpdatedAt(Instant.now().toString());
        return UserToDto(userRepo.save(user));
    }

    @Override
    public UserDto getUserDetailsById(String userId) {
        User user = findUserById(userId);
        return UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUserById(String userId) {
        User user = findUserById(userId);
        userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto UserToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public User findUserById(String userId){
        return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid User id!!!"));
    }

    public Boolean findUserByEmail(String email){
        return userRepo.findByEmail(email).isPresent();
    }
}
