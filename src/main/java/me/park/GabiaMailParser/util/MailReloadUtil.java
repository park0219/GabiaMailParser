package me.park.GabiaMailParser.util;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.GabiaMailParserMain;
import me.park.GabiaMailParser.dao.MailDAO;
import me.park.GabiaMailParser.view.MainView;

import javax.swing.*;
import java.awt.*;

import static me.park.GabiaMailParser.view.MainView.initJTable;
import static me.park.GabiaMailParser.view.MainView.jTable;

public class MailReloadUtil {

    public Runnable runnable = () -> {
        //로그인 되어 있으면 메일을 10분 마다 로딩
        if(MailUtil.user_id != null && !MailUtil.user_id.equals("")) {

            int newMailNum = MailUtil.getMailListFromServer();
            String[][] result = MailDAO.getInstance().selectMailList("", MainView.jToggleButton1.isSelected() ? "N" : "Y");
            initJTable(jTable, result);

            if(newMailNum > 0) {
                if(SystemTray.isSupported()) {
                    try {
                        displayTray(newMailNum + AppConstants.NEW_MAIL);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(null, newMailNum + AppConstants.NEW_MAIL);
            }
        }
    };

    public void displayTray(String message) {
        try {
            SystemTray tray = SystemTray.getSystemTray();

            TrayIcon trayIcon = new TrayIcon(GabiaMailParserMain.img, AppConstants.NEW_MAIL_TITLE);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip(AppConstants.NEW_MAIL_TITLE);
            tray.add(trayIcon);

            trayIcon.displayMessage(AppConstants.NEW_MAIL_TITLE, message, TrayIcon.MessageType.INFO);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
