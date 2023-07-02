package ibf2022.tfip.simplesecondbrain.Server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String Subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinceajy93@hotmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(Subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully....");
    }
}
