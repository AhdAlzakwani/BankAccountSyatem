package com.example.BankAccountSystem.Mail.Reprository;

import com.example.BankAccountSystem.Mail.Model.EmailDetails;

public interface EmailInterface {

    String sentSimpleMailToMany(EmailDetails emailDetails);
    String sendSimpleMail(EmailDetails emailDetails);
    String sendMailWithAttachmentToMany(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails emailDetails);

}
