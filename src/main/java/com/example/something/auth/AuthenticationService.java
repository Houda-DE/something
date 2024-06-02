package com.example.something.auth;

import com.example.something.Entities.User;
import com.example.something.Repositoriess.UserRepository;
import com.example.something.auth.DTO.SignInDTO;
import com.example.something.auth.DTO.SignUpDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> signin(SignInDTO dto){
        Optional<User> opUser = userRepository.findUserByEmail(dto.getEmail());
        if(opUser.isPresent()){

        }
        return null;
    }

    public ResponseEntity<?> signup(SignUpDTO dto){
        Optional<User> opUser = userRepository.findUserByEmail(dto.getEmail());
        if(opUser.isPresent()){

        }
        return null;
    }

}
