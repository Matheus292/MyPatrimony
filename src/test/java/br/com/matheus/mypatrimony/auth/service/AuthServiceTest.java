package br.com.matheus.mypatrimony.auth.service;

import br.com.matheus.mypatrimony.auth.dto.LoginDTO;
import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.auth.repository.LoginType;
import br.com.matheus.mypatrimony.general.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    AuthService service;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    ILoginRepository repository;

    LoginDTO loginDTO;

    Login login;

    @Test
    void testSignUpExistsLogin(){
      Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.of(login));

        ResponseEntity result = service.signUp(loginDTO);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void testSignUpNotExistsLoginAndCountZero(){
        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.empty());

        Mockito.when(repository.count())
                .thenReturn(0L);

        Mockito.when(repository.save(login))
                .thenReturn(login);

        ResponseEntity result = service.signUp(loginDTO);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void testSignUpNotExistsLoginAndCountNotZero(){
        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.empty());

        Mockito.when(repository.count())
                .thenReturn(1L);

        Mockito.when(repository.save(login))
                .thenReturn(login);

        ResponseEntity result = service.signUp(loginDTO);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @BeforeEach
    void configureLogin(){
        loginDTO = LoginDTO.builder()
                .login("Teste")
                .email("teste@gmail.com")
                .name("Teste")
                .password("teste123")
                .build();

        login = Login.builder()
                .login(loginDTO.getLogin())
                .email(loginDTO.getEmail())
                .insertionDate(new Date())
                .name(loginDTO.getName())
                .password(passwordEncoder.encode(loginDTO.getPassword()))
                .type(LoginType.ADMIN)
                .status(Status.ACTIVE)
                .build();
    }

}
