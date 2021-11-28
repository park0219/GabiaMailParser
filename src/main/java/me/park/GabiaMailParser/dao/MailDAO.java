package me.park.GabiaMailParser.dao;

import me.park.GabiaMailParser.model.Mail;
import me.park.GabiaMailParser.util.DBUtil;
import me.park.GabiaMailParser.util.MailUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MailDAO {

    private static MailDAO mailDAO = null;

    private final DBUtil dbUtil = DBUtil.getDBUtil();
    private ResultSet resultSet;

    public static MailDAO getInstance() {
        if(mailDAO == null) {
            mailDAO = new MailDAO();
        }
        return mailDAO;
    }

    public boolean insertMailList(Mail mail) {
        boolean result = false;
        if(mail == null) {
            return false;
        }
        try {
            // check
            if(selectDuplicateMail(mail) >= 1) {
                return false;
            }
            String sql = "INSERT INTO mail (USER_ID, MAIL_TITLE, MAIL_FROM, MAIL_DATE, MAIL_IMPORTANT, REGISTRATION_DATE) VALUES (?, ?, ?, ?, ?, NOW())";
            String[] param = {MailUtil.user_id, mail.getMail_title(), mail.getMail_from(), mail.getMail_date(), mail.getMail_important()};
            if(dbUtil.executeUpdate(sql, param) == 1) {
                result = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public int selectDuplicateMail(Mail mail) {
        int result = 0;
        if(mail == null || mail.getMail_title().length() == 0 || mail.getMail_from().length() == 0 || mail.getMail_date().length() == 0) {
            return result;
        }

        String sql = "SELECT COUNT(*) AS CNT FROM mail WHERE USER_ID = ? AND MAIL_TITLE = ? AND MAIL_FROM = ? AND MAIL_DATE = ?";
        String[] params = {MailUtil.user_id, mail.getMail_title(), mail.getMail_from(), mail.getMail_date()};
        resultSet = dbUtil.executeQuery(sql, params);
        try {
            if(resultSet.next()) {
                result = resultSet.getInt("cnt");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String[][] selectMailList(String searchWord, String importantYN) {
        String[][] result = null;

        String sql;
        String[] params;
        if(searchWord.equals("")) {
            sql = "SELECT MAIL_SEQ, USER_ID, MAIL_TITLE, MAIL_FROM, MAIL_DATE, MAIL_IMPORTANT, REGISTRATION_DATE FROM mail WHERE USER_ID = ?";
            params = new String[]{MailUtil.user_id};
        }
        else {
            sql = "SELECT MAIL_SEQ, USER_ID, MAIL_TITLE, MAIL_FROM, MAIL_DATE, MAIL_IMPORTANT, REGISTRATION_DATE FROM mail WHERE USER_ID = ? AND MAIL_TITLE = ?";
            params = new String[]{MailUtil.user_id, "%" + searchWord + "%"};
        }
        if(importantYN != null && importantYN.equals("Y")) {
            sql = sql.concat(" AND MAIL_IMPORTANT = 'Y'");
        }
        sql = sql.concat(" ORDER BY MAIL_DATE DESC");

        resultSet = dbUtil.executeQuery(sql, params);
        List<Mail> mailList = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Mail mail = new Mail(resultSet.getInt("mail_seq"), resultSet.getString("user_id"),
                        resultSet.getString("mail_title"), resultSet.getString("mail_from"), resultSet.getString("mail_date"),
                        resultSet.getString("mail_important"), resultSet.getString("registration_date"));
                mailList.add(mail);
            }
            result = new String[mailList.size()][5];
            for(int i = 0; i < mailList.size(); i++) {
                result[i][0] = mailList.get(i).getMail_title();
                result[i][1] = mailList.get(i).getMail_from();
                result[i][2] = mailList.get(i).getMail_date();
                result[i][3] = mailList.get(i).getMail_important();
                result[i][4] = Integer.toString(mailList.get(i).getMail_seq());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public boolean updateMailImportant(int mail_seq) {
        boolean result = false;

        if(mail_seq < 1) {
            return result;
        }
        try {
            String sql = "UPDATE mail SET MAIL_IMPORTANT = IF(MAIL_IMPORTANT = 'Y', 'N', 'Y') WHERE MAIL_SEQ = ?";
            String[] params = {Integer.toString(mail_seq)};
            int rowCount = dbUtil.executeUpdate(sql, params);
            if(rowCount == 1) {
                result = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
