package br.com.matheus.mypatrimony.general.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class CodeGeneratorService {

    public String generateCodeEmail(int length) {
        final String alphaNumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String text = "";

        if (length < 0)
            length = 5;

        final Random random = new Random();
        for (int index = 0; index < length; index++) {
            int value = random.nextInt(alphaNumeric.length());
            text += alphaNumeric.substring(value, value + 1);
        }
        return text;
    }

}
