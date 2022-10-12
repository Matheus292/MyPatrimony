package br.com.matheus.mypatrimony.general.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {

    public String formatterDate(Date date, String format){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        catch (Exception e){
            throw e;
        }
    }

}
