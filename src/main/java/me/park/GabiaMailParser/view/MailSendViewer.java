package me.park.GabiaMailParser.view;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.GabiaMailParserMain;
import me.park.GabiaMailParser.dao.TemplateDAO;
import me.park.GabiaMailParser.model.Template;
import me.park.GabiaMailParser.util.MailUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSendViewer extends JFrame {

    private static final long serialVersionUID = -1984182788841566838L;

    private JPanel jPanelCenter, jPanelSouth;
    private JButton sendButton, exitButton;
    private JTextField receiver;
    private JTextArea content;

    public MailSendViewer() {
        init();
    }

    private void init() {
        setTitle(AppConstants.MAILSENDVIEW_TITLE);
        setIconImage(GabiaMailParserMain.img);
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new FlowLayout());

        JPanel sendPanel = new JPanel();
        JLabel label = new JLabel(AppConstants.MAILSENDVIEW_RECEIVER);
        final JTextField text = new JTextField(30);
        sendPanel.add(label);
        sendPanel.add(text);

        JPanel subjectPanel = new JPanel();
        JLabel label2 = new JLabel(AppConstants.MAILSENDVIEW_MAILTITLE);
        final JTextField text2 = new JTextField(30);
        subjectPanel.add(label2);
        subjectPanel.add(text2);

        Template template = TemplateDAO.getInstance().selectTemplate();
        JPanel contentPanel = new JPanel();
        JLabel lblContent = new JLabel(AppConstants.MAILSENDVIEW_CONTENT);
        final JTextArea text4 = new JTextArea(template.getTemplate_content(), 10, 30);

        JScrollPane scroll = new JScrollPane(text4);
        contentPanel.add(lblContent);
        contentPanel.add(scroll);

        jPanelCenter.add(sendPanel);
        jPanelCenter.add(subjectPanel);
        jPanelCenter.add(contentPanel);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        sendButton = new JButton(AppConstants.MAILSENDVIEW_SENDBUTTON);
        sendButton.addActionListener(e -> {
            String sendEmail = text.getText().trim();
            String title = text2.getText().trim();
            String content = text4.getText().trim();
            if(sendEmail.equals("") && validEmail(sendEmail)) {
                JOptionPane.showMessageDialog(null, AppConstants.SENDMAIL_FAIL);
                return;
            }
            if(title.equals("")) {
                JOptionPane.showMessageDialog(null, AppConstants.TITLE_FAIL);
                return;
            }
            if(content.equals("")) {
                JOptionPane.showMessageDialog(null, AppConstants.CONTENT_FAIL);
                return;
            }

            if((MailUtil.sendMail(title, sendEmail, content))) {
                JOptionPane.showMessageDialog(null, AppConstants.MAILSEND_SUCCESS);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, AppConstants.MAILSEND_FAIL);
            }

        });

        jPanelSouth.add(sendButton);
        exitButton = new JButton(AppConstants.EXITBUTTON);
        exitButton.addActionListener(e -> dispose());
        jPanelSouth.add(exitButton);

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 200, 700, 500);
        setResizable(false);
        setVisible(true);
    }

    public static boolean validEmail(String email) {
        boolean result = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            result = true;
        }
        return result;
    }

}
