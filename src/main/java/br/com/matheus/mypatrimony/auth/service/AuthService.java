package br.com.matheus.mypatrimony.auth.service;

import br.com.matheus.mypatrimony.auth.dto.LoginDTO;
import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.auth.repository.LoginType;
import br.com.matheus.mypatrimony.general.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private ILoginRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity signUp(LoginDTO dto){

        Optional<Login> loginPesquisado = repository.findByLogin(dto.getLogin());

        if(loginPesquisado.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Login login = Login.builder()
                .login(dto.getLogin())
                .insertionDate(new Date())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .status(Status.ACTIVE)
                .build();

        if(repository.count() == 0)
            login.setType(LoginType.ADMIN);
        else
            login.setType(LoginType.COMMON);

        repository.save(login);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
