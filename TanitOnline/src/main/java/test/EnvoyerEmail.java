package test;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.Transport;


public class EnvoyerEmail {
    private String username = "chaymariahi961@gmail.com";
    private String password = "chaymariahi123";
    public void envoyer (){
        // etape1: creation d'une session

        Properties props = new Properties();

        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("votre_mail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("chaymariahi961@gmail.com"));
            message.setSubject("Test email");
            message.setText("Bonjour, ce message est un test ...");
            // Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } }
    //Etape 4 : Tester la méthode
    public static void main(String[] args) {
        EnvoyerEmail test = new EnvoyerEmail();
        test.envoyer();
    } }

