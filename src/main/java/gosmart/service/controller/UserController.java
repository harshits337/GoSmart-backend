package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.UserDto;
import gosmart.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/rest/api/v1/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userDto));
    }

    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetailsById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Deleted Successfully!!!",true));
    }
}
