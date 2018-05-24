package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

@WebServlet(name = "MailServlet")
public class MailServlet extends HttpServlet {


        private final static String MAILER_VERSION = "Java";
        public static boolean envoyerMailSMTP(String serveur, boolean debug) {
            boolean result = false;
            try {
                Properties prop = System.getProperties();
                prop.put("mail.smtp.host", serveur);
                Session session = Session.getDefaultInstance(prop,null);
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("moi@chez-moi.fr"));
                InternetAddress[] internetAddresses = new InternetAddress[1];
                internetAddresses[0] = new InternetAddress("moi@chez-moifr");
                message.setRecipients(Message.RecipientType.TO,internetAddresses);
                message.setSubject("Test");
                message.setText("test mail");
                message.setHeader("X-Mailer", MAILER_VERSION);
                message.setSentDate(new Date());
                session.setDebug(debug);
                Transport.send(message);
                result = true;
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return result;
        }

        public static void main(String[] args) {
          //  TestMail.envoyerMailSMTP("10.10.50.8",true);
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emetteur = "valentin";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
