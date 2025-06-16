package sistemadinotifiche;
import exceptions.SistemaNotificheException;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {

    private static final String MITTENTE = "sistemataskdidattici@gmail.com";

    private MailSender() {}
    
    public static void inviaValutazioneTask(String destinatario, String task, String valutazione, String commento) {
        final String password = "chcu ebsc kqdl xgpd"; /// Usa una App Password, non la password normale
        final String nomeMittente = "Sistema di Valutazione"; /// Nome personalizzato che apparirà

        Properties props = getGmailSmtpProperties();

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MITTENTE, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            /// Imposta il mittente con nome personalizzato
            message.setFrom(new InternetAddress(MITTENTE, nomeMittente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Valutazione del task: " + task);

            String testo = String.format(
                    "Ciao,%n%nIl task '%s' è stato valutato.%n%nRisultato: %s%nDescrizione: %s%n%nAccedi alla piattaforma per maggiori dettagli.",
                    task, valutazione, commento
            );
            message.setText(testo);

            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new SistemaNotificheException();
        }
    }

    public static Message initMail(){
        final String password = "chcu ebsc kqdl xgpd"; /// Usa una App Password, non la password normale

        Properties props = getGmailSmtpProperties();

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MITTENTE, password);
            }
        });

        return new MimeMessage(session);

    }

    private static Properties getGmailSmtpProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }


    public static void inviaCreazioneTask(String destinatario, String task, String commento) {
        Message message = initMail();
        final String nomeMittente = "Sistema di Valutazione"; /// Nome personalizzato che apparirà

        try {
            /// Imposta il mittente con nome personalizzato
            message.setFrom(new InternetAddress(MITTENTE, nomeMittente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Creazione del task: " + task);

            String testo = String.format(
                    "Ciao,%n%nIl task '%s' è stato appena creato.%n%nDescrizione: %s%n%nAccedi alla piattaforma per maggiori dettagli.",
                    task, commento
            );
            message.setText(testo);

            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new SistemaNotificheException();
        }
    }
}