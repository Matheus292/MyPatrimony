package br.com.matheus.mypatrimony;

import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.auth.repository.LoginType;
import br.com.matheus.mypatrimony.auth.service.AuthService;
import br.com.matheus.mypatrimony.config.ConfigProperties;
import br.com.matheus.mypatrimony.general.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class MyPatrimonyApplication {

	@Autowired
	private ILoginRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(MyPatrimonyApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ConfigProperties getConfigProperties(){
		return new ConfigProperties();
	}
}
