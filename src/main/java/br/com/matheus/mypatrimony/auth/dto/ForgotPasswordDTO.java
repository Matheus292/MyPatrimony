package br.com.matheus.mypatrimony.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPasswordDTO {

    @NotBlank(message = "Login não pode estar vazio")
    private String login;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail não pode estar vazio")
    private String email;

}
