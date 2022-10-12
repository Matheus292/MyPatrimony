package br.com.matheus.mypatrimony.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    private String to;
    private String from;
    private String subject;
    private String content;
    private Map< String, Object > model;

}
