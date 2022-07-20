package br.com.matheus.mypatrimony.auth.service;

import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private ILoginRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Login save(Login login){
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return repository.save(login);
    }
}
