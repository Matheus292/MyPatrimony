package br.com.matheus.mypatrimony.security.filter;

import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.config.ConfigProperties;
import br.com.matheus.mypatrimony.error.exception.ApiException;
import br.com.matheus.mypatrimony.security.data.LoginData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private final ConfigProperties properties;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticateFilter(AuthenticationManager authenticationManager, ConfigProperties configProperties) {
        this.authenticationManager = authenticationManager;
        this.properties =configProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login object = new ObjectMapper().readValue(request.getInputStream(), Login.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(object.getLogin(), object.getPassword(), new ArrayList<>())
            );
        }
        catch (IOException e) {
            throw new ApiException(HttpStatus.BAD_GATEWAY, "Corpo de entrada inválido!");
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginData object = (LoginData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(object.getUsername())
                .withExpiresAt(
                    new Date(System.currentTimeMillis() + (properties.getTokenExpiration() * 1000 * 60 ))
                ).sign(Algorithm.HMAC256(properties.getSecretKey()));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}