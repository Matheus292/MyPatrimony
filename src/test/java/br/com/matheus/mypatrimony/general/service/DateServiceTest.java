package br.com.matheus.mypatrimony.general.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class DateServiceTest {

    @Autowired
    DateService service;

    @Test
    void testFormatterDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, Calendar.OCTOBER, 29);
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 55);
        calendar.set(Calendar.SECOND, 0);

        Assertions.assertEquals("29/10/1997 11:55:00", service.formatterDate(calendar.getTime(), "dd/MM/yyyy HH:mm:ss"));
    }

    @Test
    void testFormatterDateError(){
        Assertions.assertThrows(NullPointerException.class, () ->{
            service.formatterDate(null, "dd/MM/yyyy HH:mm:ss");
        });
    }

}
