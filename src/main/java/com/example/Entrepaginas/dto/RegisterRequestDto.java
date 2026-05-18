package com.example.Entrepaginas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    //no permite estar en blanco, restricción de tamaño también
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    //no permite estar en blanco
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}

