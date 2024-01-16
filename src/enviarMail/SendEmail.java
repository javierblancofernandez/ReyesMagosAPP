/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enviarMail;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author javib
 */
public class SendEmail {
    private Properties props = new Properties();
    private Session session = null;
    private String EMAILSMTP = "javier.blancofernandez1974@gmail.com";
    private String PASSWORD = "oazifoflxosmqisq";
    
    public SendEmail(){
    
        // Set the email properties
        this.props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        this.props.put("mail.smtp.port", "587"); // Replace with the appropriate port
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true"); // Use TLS for secure connection
        
        // Create a Session object with the email credentials
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAILSMTP, "oazifoflxosmqisq");
            }
        });
    }
    
    public void envioDeEmail(String email) {
                try {
            /*BodyPart texto = new MimeBodyPart();
            texto.setText("Tus deseos se cumplirán --> mira el archivo adjunto");*/

            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/javib/Desktop/CartaReyesMagos.pdf")));
            adjunto.setFileName("CartaReyesMagos.pdf");
            //MimeMultipart multiParte = new MimeMultipart();

            /*multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);*/
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(new File("C:/Users/javib/Desktop/reyes_magos.png"));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("reyes_magos.png");
            messageBodyPart.setDisposition(MimeBodyPart.INLINE);
            messageBodyPart.setHeader("Content-ID", "<vogue>");
            multipart.addBodyPart(adjunto);
            multipart.addBodyPart(messageBodyPart);

            // create bodypart with html content and reference to the content-id
            messageBodyPart = new MimeBodyPart();
            String htmlText = "<p> Tus deseos se cumpliran  --> mira el archivo adjunto </p><br><br><img src=\"cid:vogue\">";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            // Create a MimeMessage
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress("javier.blancofernandez1974@gmail.com"));

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the email subject
            message.setSubject("🐪🐪🐪 Esto es un mensaje Mágico 亗 亗 亗");

            // Set the email content
            //message.setText("This is the email content.");
            message.setContent(multipart);
            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }   

 }

