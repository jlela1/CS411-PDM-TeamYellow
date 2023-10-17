package backend.database;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class sendEmail {
    public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentFilePath) {
        final String username = "parkingdemandmanagement@gmail.com";
        final String password = "brsx bwub geoo gzyv";
        String host = "smtp.gmail.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Create the email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            // Create the attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFilePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(attachmentFilePath); // Set the attachment's file name

            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email with attachment sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String to = "klee028@odu.edu"; // Replace with the recipient's email address
        String subject = "Automated Message From Your Friends At PDM";
        String body = "This email includes an attachment of the latest push to the database. Please insert it into DB.";

        // Provide the file path of the attachment
        String attachmentFilePath = "src/trendy.txt";
        String userDataFilePath = "src/backend/database/user_data.txt";
        sendEmailWithAttachment(to, subject, body, attachmentFilePath);
        sendEmailWithAttachment(to, subject, body, userDataFilePath);

    }

} 
