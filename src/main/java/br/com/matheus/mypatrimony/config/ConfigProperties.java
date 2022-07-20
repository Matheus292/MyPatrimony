package br.com.matheus.mypatrimony.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ConfigProperties {

    @Value("${secret.key}")
    private String secretKey;

    @Value("${minutes_expiration_token}")
    private int tokenExpiration;
}
