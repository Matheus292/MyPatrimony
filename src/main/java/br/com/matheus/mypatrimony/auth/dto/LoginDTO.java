package br.com.matheus.mypatrimony.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O login não pode estar vazio")
    private String login;

    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank
    @Size(min = 6, max = 15, message = "A senha deve conter de 6 a 15 caracteres")
    private String password;

}
