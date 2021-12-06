package me.park.GabiaMailParser.util;

import com.sun.mail.util.BASE64DecoderStream;
import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.dao.MailDAO;
import me.park.GabiaMailParser.model.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static me.park.GabiaMailParser.util.Base64Util.base64Decode;

public class MailUtil {

    private static Session session;
    private static Store store;
    private static Folder folder;
    private static Properties properties;
    public static String user_id;

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

            session = Session.getDefaultInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(id, pw);
                        }
                    });

            //session = Session.getInstance(properties, null);

            store = session.getStore("pop3");
            store.connect(AppConstants.POPSERVER, id, pw);
            folder = store.getFolder("INBOX");
            user_id = id;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static int getMailListFromServer() {

        int result = 0;

        try {
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();

            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add(FetchProfile.Item.FLAGS);
            fp.add("X-Mailer");
            folder.fetch(messages, fp);

            for(Message message : messages) {
                String date = getMailDate(message);
                String title = getMailTitle(message);
                String from = getMailFrom(message).replaceAll("\"", "");
                //String body = getMailBody(message);

                Mail mail = new Mail(user_id, title, from, date, "N");

                if(MailDAO.getInstance().insertMailList(mail)) {
                    result++;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getMailBody(Message message) throws Exception {

        Object content = message.getContent();
        MimeMultipart multiPart;
        if(content instanceof Multipart) {
            multiPart = (MimeMultipart) content;
            int bodyCount = multiPart.getCount();

            StringBuilder body = new StringBuilder();
            for(int i = 0; i < bodyCount; i++) {
                BodyPart bodyPart = multiPart.getBodyPart(i);
                Object obj = bodyPart.getContent();

                if(obj instanceof BASE64DecoderStream) {
                    String[] disp = bodyPart.getHeader("Content-Disposition");
                    BASE64DecoderStream newObj = (BASE64DecoderStream) obj;
                    body.append(MimeUtility.decodeText(bodyPart.getFileName()));
                }
                else {
                    body.append(obj.toString());
                }
            }
            return body.toString();
        }
        else {
            return content.toString();
        }
    }

    private static String getMailDate(Message message) throws MessagingException {
        Date date = message.getSentDate();
        if(date == null) {
            return "";
        }
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(date);
        }
    }

    private static String getMailTitle(Message message) throws MessagingException {
        String[] subject = message.getHeader("subject");
        if(subject == null || subject[0] == null) {
            return "";
        }
        else {
            return base64Decode(subject[0]);
        }
    }

    private static String getMailFrom(Message message) throws MessagingException {
        Address address = message.getFrom()[0];
        if(address == null) {
            return "";
        }
        else {
            return base64Decode(address.toString());
        }
    }

    public static boolean sendMail(String title, String sendEmail, String content) {

        boolean result = false;

        try {
            //메세지 설정
            Message msg = new MimeMessage(session);

            //보내는사람 받는사람 설정
            //보내는 사람 이메일 입력
            msg.setFrom(new InternetAddress(user_id));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmail, false));
            msg.setSubject(title);
            msg.setContent(content, "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
            result = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
