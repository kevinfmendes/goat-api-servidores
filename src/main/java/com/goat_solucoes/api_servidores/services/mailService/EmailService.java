package com.goat_solucoes.api_servidores.services.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmail(String destinatario, String assunto, String mensagem){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(mensagem);
            javaMailSender.send(message);
            return "Email enviado com sucesso";
        } catch (Exception e){
            return "Erro no envio do email" +e.getLocalizedMessage();
        }
    }
}
