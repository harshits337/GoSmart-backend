package gosmart.service.controller;

import gosmart.service.dto.ApiResponse;
import gosmart.service.dto.JwtAuthRequest;
import gosmart.service.dto.JwtResponse;
import gosmart.service.dto.UserDto;
import gosmart.service.exceptions.ResourceNotFoundException;
import gosmart.service.service.UserService;
import gosmart.service.utils.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/rest/api/v1/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
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

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);
        UserDto userDto = userService.getUserDetailsByEmail(userDetails.getUsername());
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setUserId(userDto.getId());
        return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {

            this.authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Detials !!");
            throw new ResourceNotFoundException("Invalid username or password !!");
        }

    }

    @GetMapping("/me/{userId}")
    public ResponseEntity<UserDto> me(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserDetailsById(userId));
    }
}
