package br.com.matheus.mypatrimony.security.filter;

import br.com.matheus.mypatrimony.config.ConfigProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class JwtValidateFilter extends BasicAuthenticationFilter {

    private static final String HEADER_ATTRIBUTE = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Autowired
    private final ConfigProperties properties;

    public JwtValidateFilter(AuthenticationManager authenticationManager, ConfigProperties properties) {
        super(authenticationManager);
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String attr = request.getHeader(HEADER_ATTRIBUTE);

        if(Objects.isNull(attr) || !attr.startsWith(PREFIX)){
            chain.doFilter(request, response);
            return ;
        }

        String token = attr.replace(PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = null;
        try {
           user = JWT.require(Algorithm.HMAC256(properties.getSecretKey())).build().verify(token).getSubject();
        } catch (Exception jwtDecodeException){
           user = "";
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
