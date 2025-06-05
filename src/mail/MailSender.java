package mail;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {

    public static int inviaValutazioneTask(String destinatario, String task, String valutazione, String commento) {
        int esito=0;
        final String mittente = "ernestocifuni29@gmail.com";
        final String password = "bzoi rmpd vozm irkr"; /// Usa una App Password, non la password normale
        final String nomeMittente = "Sistema di Valutazione"; /// Nome personalizzato che apparirà

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mittente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            /// Imposta il mittente con nome personalizzato
            message.setFrom(new InternetAddress(mittente, nomeMittente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Valutazione del task: " + task);

            String testo = String.format(
                    "Ciao,\n\nIl task '%s' è stato valutato.\n\nRisultato: %s\nDescrizione: %s\n\nAccedi alla piattaforma per maggiori dettagli.",
                    task, valutazione, commento
            );
            message.setText(testo);

            Transport.send(message);
            System.out.println("Email inviata con successo");
        } catch (MessagingException e) {
            esito=-1;
        } catch (UnsupportedEncodingException e) {
            esito=-1;
        }
        return esito;
    }

    public static Message initMail(){
        final String mittente = "ernestocifuni29@gmail.com";
        final String password = "bzoi rmpd vozm irkr"; /// Usa una App Password, non la password normale

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mittente, password);
            }
        });

        return new MimeMessage(session);

    }

    public static int inviaCreazioneTask(String destinatario, String task, String commento) {
        Message message = initMail();
        int esito=0;
        final String mittente = "ernestocifuni29@gmail.com";
        final String nomeMittente = "Sistema di Valutazione"; /// Nome personalizzato che apparirà

        try {
            /// Imposta il mittente con nome personalizzato
            message.setFrom(new InternetAddress(mittente, nomeMittente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Creazione del task: " + task);

            String testo = String.format(
                    "Ciao,\n\nIl task '%s' è stato appena creato.\n\nDescrizione: %s\n\nAccedi alla piattaforma per maggiori dettagli.",
                    task, commento
            );
            message.setText(testo);

            Transport.send(message);
            System.out.println("Email inviata con successo");
        } catch (MessagingException e) {
            esito=-1;
        } catch (UnsupportedEncodingException e) {
            esito=-1;
        }
        return esito;
    }
}