package me.park.GabiaMailParser.dao;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.model.Template;
import me.park.GabiaMailParser.util.DBUtil;
import me.park.GabiaMailParser.util.MailUtil;

import javax.swing.*;
import java.sql.ResultSet;

public class TemplateDAO {

    private static TemplateDAO templateDAO = null;

    private final DBUtil dbUtil = DBUtil.getDBUtil();
    private ResultSet resultSet;

    public static TemplateDAO getInstance() {
        if(templateDAO == null) {
            templateDAO = new TemplateDAO();
        }
        return templateDAO;
    }

    public boolean updateTemplate(Template template) {
        boolean result = false;
        if(template == null) {
            return false;
        }
        try {
            String sql = "INSERT INTO template (USER_ID, TEMPLATE_CONTENT, LAST_MODIFY) VALUES (?, ?, NOW()) ON DUPLICATE KEY UPDATE TEMPLATE_CONTENT = ?, LAST_MODIFY = NOW();";
            String[] param = {MailUtil.user_id, template.getTemplate_content(), template.getTemplate_content()};
            int resultNum = dbUtil.executeUpdate(sql, param);
            if(resultNum == 1 || resultNum == 2) {
                result = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, AppConstants.DB_CONNECTION_ERROR);
            System.exit(0);
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

    public Template selectTemplate() {
        Template result = null;

        String sql = "SELECT USER_ID, TEMPLATE_CONTENT, LAST_MODIFY FROM template WHERE USER_ID = ?";
        String[] params = new String[]{MailUtil.user_id};
        resultSet = dbUtil.executeQuery(sql, params);
        try {
            if(resultSet.next()) {
                result = new Template(resultSet.getString("user_id"), resultSet.getString("template_content"), resultSet.getString("last_modify"));
            }
            else {
                result = new Template();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, AppConstants.DB_CONNECTION_ERROR);
            System.exit(0);
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
