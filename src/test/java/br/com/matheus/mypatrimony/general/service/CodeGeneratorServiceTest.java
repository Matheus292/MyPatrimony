package br.com.matheus.mypatrimony.general.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGeneratorServiceTest {

    @Autowired
    CodeGeneratorService service;

    @Test
    void testGenerateCodeEmail(){
        Assertions.assertNotNull(service.generateCodeEmail(10));
        Assertions.assertEquals(10, service.generateCodeEmail(10).length());
        Assertions.assertEquals(5, service.generateCodeEmail(-5).length());
    }

}
