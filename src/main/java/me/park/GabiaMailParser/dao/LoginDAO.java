package me.park.GabiaMailParser.dao;

import me.park.GabiaMailParser.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private static LoginDAO loginDAO = null;

    private final DBUtil dbUtil = DBUtil.getDBUtil();
    private ResultSet resultSet;

    public static LoginDAO getInstance() {
        if(loginDAO == null) {
            loginDAO = new LoginDAO();
        }
        return loginDAO;
    }

    public boolean insertLoginInfo(String id) {
        boolean result = false;

        if(id == null || id.length() == 0) {
            return false;
        }
        String sql = "INSERT INTO login_info (USER_ID, LOGIN_DATE) VALUES (?, NOW())";
        String[] params = {id};

        try {
            if(dbUtil.executeUpdate(sql, params) == 1) {
                result = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
