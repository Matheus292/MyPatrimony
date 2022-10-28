package br.com.matheus.mypatrimony.auth.controller;

import br.com.matheus.mypatrimony.auth.dto.ForgotPasswordDTO;
import br.com.matheus.mypatrimony.auth.dto.LoginDTO;
import br.com.matheus.mypatrimony.auth.dto.NewPasswordDTO;
import br.com.matheus.mypatrimony.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/signUp")
    public ResponseEntity signup(@RequestBody @Valid LoginDTO dto){
        return service.signUp(dto);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody @Valid ForgotPasswordDTO dto){
        return service.forgotPassword(dto);
    }

    @PutMapping("/newPassword")
    public ResponseEntity newPassword(@RequestBody @Valid NewPasswordDTO dto){
        return service.newPassword(dto);
    }

}