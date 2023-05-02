package com.example.BankAccountSystem.Mail.Model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private List<String> recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
