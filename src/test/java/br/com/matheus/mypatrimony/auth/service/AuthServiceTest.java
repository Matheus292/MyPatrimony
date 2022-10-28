package br.com.matheus.mypatrimony.auth.service;

import br.com.matheus.mypatrimony.auth.dto.ForgotPasswordDTO;
import br.com.matheus.mypatrimony.auth.dto.LoginDTO;
import br.com.matheus.mypatrimony.auth.dto.NewPasswordDTO;
import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.auth.repository.LoginType;
import br.com.matheus.mypatrimony.general.enums.Status;
import br.com.matheus.mypatrimony.general.service.CodeGeneratorService;
import br.com.matheus.mypatrimony.general.service.DateService;
import br.com.matheus.mypatrimony.mail.service.MailService;
import br.com.matheus.mypatrimony.message.ApiMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    AuthService service;

    @Mock
    CodeGeneratorService codeGeneratorService;

    @Mock
    DateService dateService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    ILoginRepository repository;

    @Mock
    MailService mailService;

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

    @Test
    void testForgotPasswordNotFound(){
        ForgotPasswordDTO dto = ForgotPasswordDTO.builder()
                .email("xpto@gmail.com")
                .login("teste")
                .build();

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.empty());

        ResponseEntity entity = service.forgotPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        assertEquals("Usuário não existe", message.getMessage());
    }

    @Test
    void testForgotPasswordBadRequest(){
        ForgotPasswordDTO dto = ForgotPasswordDTO.builder()
                .email("xpto@gmail.com")
                .login("teste")
                .build();

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.of(login));

        ResponseEntity entity = service.forgotPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        assertEquals("E-mail não pertence ao usuário", message.getMessage());
    }

    @Test
    void testForgotPasswordOk(){
        ForgotPasswordDTO dto = ForgotPasswordDTO.builder()
                .email("teste@gmail.com")
                .login("Teste")
                .build();

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.of(login));

        ResponseEntity entity = service.forgotPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("E-mail enviado para " + login.getEmail(), message.getMessage());
    }

    @Test
    void testNewPasswordNotFound(){

        NewPasswordDTO dto = NewPasswordDTO.builder()
                .login("tet")
                .passoword("asa")
                .code("xp")
                .build();

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.empty());

        ResponseEntity entity = service.newPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        assertEquals("Usuário não existe", message.getMessage());
    }

    @Test
    void testNewPasswordBadRequest(){

        NewPasswordDTO dto = NewPasswordDTO.builder()
                .login("teste")
                .passoword("teste")
                .code("xyz")
                .build();

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.of(login));

        ResponseEntity entity = service.newPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        assertEquals("Código inválido", message.getMessage());
    }

    @Test
    void testNewPasswordOk(){

        NewPasswordDTO dto = NewPasswordDTO.builder()
                .login("teste")
                .passoword("teste")
                .code("abx")
                .build();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);

        login.setExpirationDate(calendar.getTime());

        Mockito.when(repository.findByLogin(Mockito.any()))
                .thenReturn(Optional.of(login));

        Mockito.when(repository.save(login))
                .thenReturn(login);

        ResponseEntity entity = service.newPassword(dto);
        ApiMessage message = (ApiMessage) entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("Senha alterada com sucesso.", message.getMessage());
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
                .code("abx")
                .build();
    }

}
