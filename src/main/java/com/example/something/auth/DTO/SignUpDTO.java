package com.example.something.auth.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    private String username;

    private String password;

    private String email;
}
