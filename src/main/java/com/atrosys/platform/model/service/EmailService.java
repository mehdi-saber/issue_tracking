package com.atrosys.platform.model.service;

import com.atrosys.platform.model.to.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by asgari on 12/26/17.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    public void send(Email eParams) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
        helper.setReplyTo(eParams.getFrom());
        helper.setFrom(eParams.getFrom());
        helper.setSubject(eParams.getSubject());
        helper.setText(eParams.getMessage(), eParams.isHtml());
        if (eParams.getCc().size() > 0) {
            helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
        }
        if (eParams.getBcc().size() > 0) {
            helper.setBcc(eParams.getBcc().toArray(new String[eParams.getBcc().size()]));
        }
        if (eParams.getAttachment()!=null){
     try {

         helper.addAttachment(eParams.getAttachment(),new ClassPathResource("email-attachments/"+eParams.getAttachment()));
     }catch (Exception e){
         e.printStackTrace();
     }
        }
        mailSender.send(message);
    }


}
