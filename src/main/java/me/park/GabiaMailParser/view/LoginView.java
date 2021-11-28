package me.park.GabiaMailParser.view;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.dao.LoginDAO;
import me.park.GabiaMailParser.util.DBUtil;
import me.park.GabiaMailParser.util.MailUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame {

    private static final long serialVersionUID = -5278598737087831336L;
    private JPanel jPanelCenter, jPanelSouth;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton, resetButton;

    public LoginView() {
        init();
    }

    private void init() {
        this.setTitle(AppConstants.LOGIN_TITLE);

        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(3, 2));
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));
        username = new JTextField();
        jPanelCenter.add(username);
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
        password = new JPasswordField();

        //엔터키로 로그인 실행
        password.addKeyListener(new LoginListener());
        jPanelCenter.add(password);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        loginButton = new JButton(AppConstants.LOGIN);
        loginButton.addActionListener(e -> check());
        jPanelSouth.add(loginButton);

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(450, 250, 375, 140);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class LoginListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                check();
            }
        }
    }

    private void check() {

        String id = username.getText();
        String pw = String.valueOf(password.getPassword());
        if(MailUtil.mailServerLogin(id, pw)) {
            
            //로그인 기록 로그 입력
            LoginDAO.getInstance().insertLoginInfo(id);

            dispose();
            new MainView();
        }
        else {
            username.setText("");
            password.setText("");
            JOptionPane.showMessageDialog(null, AppConstants.LOGIN_FAIL);
        }
    }

}