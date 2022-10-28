package br.com.matheus.mypatrimony.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O login não pode estar vazio")
    private String login;

    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    @NotBlank(message = "E-mail não pode estar vazio")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, max = 15, message = "A senha deve conter de 6 a 15 caracteres")
    private String password;

}
