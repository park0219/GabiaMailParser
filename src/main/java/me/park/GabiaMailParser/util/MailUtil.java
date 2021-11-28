package me.park.GabiaMailParser.util;

import me.park.GabiaMailParser.AppConstants;

import javax.mail.*;
import java.util.Properties;

public class MailUtil {

    private static Session session;
    private static Store store;
    private static Folder folder;
    private static Properties properties;

    public static boolean mailServerLogin(String id, String pw) {

        try {
            properties = System.getProperties();
            properties.setProperty("mail.smtp.host", AppConstants.SMTPSERVER);
            properties.setProperty("mail.smtp.socketFactory.class", AppConstants.SSL_FACTORY);
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.port", AppConstants.SMTPPORT);
            properties.setProperty("mail.smtp.socketFactory.port", AppConstants.SMTPPORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.transport.protocol", "smtp");

            /* session = Session.getDefaultInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(id, pw);
                        }
                    });*/

            session = Session.getInstance(properties, null);

            store = session.getStore("pop3");
            store.connect(AppConstants.POPSERVER, id, pw);
            folder = store.getFolder("INBOX");
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
