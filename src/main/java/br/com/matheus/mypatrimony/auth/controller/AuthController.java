package br.com.matheus.mypatrimony.auth.controller;

import br.com.matheus.mypatrimony.auth.dto.LoginDTO;
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
    public ResponseEntity forgotPassword(){
        return null;
    }

    @PostMapping("/newPassword")
    public ResponseEntity newPassword(){
        return null;
    }

}