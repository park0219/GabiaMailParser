package me.park.GabiaMailParser.util;

import me.park.GabiaMailParser.AppConstants;

import java.sql.*;

public class DBUtil {

    private static DBUtil dbUtil;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DBUtil() {
    }

    public static DBUtil getDBUtil() {
        if(dbUtil == null) {
            dbUtil = new DBUtil();
        }
        return dbUtil;
    }

    public int executeUpdate(String sql) {
        int result = -1;
        if(getConn() == null) {
            return result;
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] params) {
        int result = -1;
        if(getConn() == null) {
            return result;
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            result = preparedStatement.executeUpdate();
            close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet executeQuery(String sql) {
        if(getConn() == null) {
            return null;
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet executeQuery(String sql, Object[] params) {
        if(getConn() == null) {
            return null;
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public boolean exeute(String sql) {
        if(getConn() == null) {
            return false;
        }
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    private Connection getConn() {
        try {
            if(connection == null || connection.isClosed()) {
                Class.forName(AppConstants.JDBC_DRIVER);
                connection = DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME, AppConstants.JDBC_PASSWORD);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
