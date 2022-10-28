package br.com.matheus.mypatrimony.mail.service;

import br.com.matheus.mypatrimony.mail.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    public void sendMail(EmailDTO mail){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());
        msg.setFrom(mail.getFrom());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getContent());

        sender.send(msg);
    }

}
