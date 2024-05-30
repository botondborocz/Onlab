package goalzone.controller;

import goalzone.dto.AdminUserDto;
import goalzone.dto.AverageUserDto;
import goalzone.dto.LoginResponse;
import goalzone.service.AdminUserService;
import goalzone.service.AverageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AdminUserService adminUserService;
    private final AverageUserService averageUserService;

    @PostMapping("/login/user")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AverageUserDto user){
        try{
            averageUserService.signIn(user.getUsername(), user.getPassword());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        LoginResponse response=new LoginResponse();
        response.setUsername(user.getUsername());
        response.setType("averageUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody AdminUserDto user){
        try{
            adminUserService.signIn(user.getUsername(), user.getPassword());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        LoginResponse response=new LoginResponse();
        response.setUsername(user.getUsername());
        response.setType("adminUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup/user")
    public void signUpUser(@RequestBody AverageUserDto user){
        try{
            averageUserService.signUp(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/signup/admin")
    public void signUpAdmin(@RequestBody AdminUserDto user) {
        try{
            adminUserService.signUp(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
