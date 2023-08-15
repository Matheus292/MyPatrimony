package br.com.matheus.mypatrimony.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionLoginDTO {

    private Long id;
    private String login;
    private String name;

}
