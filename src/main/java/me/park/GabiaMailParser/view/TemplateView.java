package me.park.GabiaMailParser.view;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.GabiaMailParserMain;
import me.park.GabiaMailParser.dao.TemplateDAO;
import me.park.GabiaMailParser.model.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemplateView extends JFrame {
    private static final long serialVersionUID = 5292738820127102731L;

    private JPanel jPanelCenter, jPanelSouth;
    private JButton updateButton, exitButton;
    private JTextArea jTextArea;

    private Template template;

    public TemplateView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.TEMPLATEVIEW_TITLE);
        setIconImage(GabiaMailParserMain.img);

        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new BorderLayout());

        JLabel jLabel = new JLabel(AppConstants.TEMPLATEVIEW_LABEL);
        jPanelCenter.add(jLabel, BorderLayout.NORTH);

        //기존 템플릿 가져오기
        template = TemplateDAO.getInstance().selectTemplate();
        jTextArea = new JTextArea(template.getTemplate_content());
        jPanelCenter.add(new JScrollPane(jTextArea), BorderLayout.CENTER);

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        updateButton = new JButton(AppConstants.TEMPLATEVIEW_UPDATEBUTTON);
        updateButton.addActionListener(e -> {
            template.setTemplate_content(jTextArea.getText());
            if(TemplateDAO.getInstance().updateTemplate(template)) {
                dispose();
                JOptionPane.showMessageDialog(null, AppConstants.TEMPLATEVIEW_UPDATESUCCESS);
            }
            else {
                JOptionPane.showMessageDialog(null, AppConstants.TEMPLATEVIEW_UPDATEFAIL);
            }
        });
        jPanelSouth.add(updateButton);
        exitButton = new JButton(AppConstants.EXITBUTTON);
        exitButton.addActionListener(e -> dispose());
        jPanelSouth.add(exitButton);

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 200, 600, 400);
        setResizable(false);
        setVisible(true);
    }
}
