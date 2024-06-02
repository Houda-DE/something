package com.example.something.auth;

import com.example.something.auth.DTO.SignInDTO;
import com.example.something.auth.DTO.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    private ResponseEntity<?> signup(@RequestBody SignUpDTO req){
        try{
            String password = req.getPassword();
            String email = req.getEmail();
            String username = req.getUsername();
            if(password != null && email != null && username != null) {
                ResponseEntity<?> res =  authenticationService.signup(req);
                if (res != null) {
                    return ResponseEntity.ok(res);
                } else {
                    return new ResponseEntity<>("user not created", HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>("one field at least is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    private ResponseEntity<?> signin(@RequestBody SignInDTO req){
        try{
            String password = req.getPassword();
            String email = req.getEmail();
            if(password != null && email != null) {
                ResponseEntity<?> res =  authenticationService.signin(req);
                if (res != null) {
                    return ResponseEntity.ok(res);
                } else {
                    return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>("one field at least is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
