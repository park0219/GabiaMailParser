package me.park.GabiaMailParser.view;

import me.park.GabiaMailParser.AppConstants;
import me.park.GabiaMailParser.dao.MailDAO;
import me.park.GabiaMailParser.util.MailUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JFrame {

    private static final long serialVersionUID = 5870864087464173884L;
    private JPanel jPanelNorth, jPanelCenter;
    private JButton jButtonAdd, jButtonUpdate, jButtonFind;
    private JTextField condition;
    public static JTable jTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    public static String[] columns = {AppConstants.MAIL_TITLE, AppConstants.MAIL_FROM, AppConstants.MAIL_DATE, AppConstants.MAIL_IMPORTANT, "MAIL_SEQ"};

    public MainView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.MAINVIEW_TITLE);

        // 윗쪽 패널
        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new GridLayout(1, 5));
        condition = new JTextField();
        condition.addKeyListener(new FindListener());
        jPanelNorth.add(condition);
        // 찾기 버튼
        jButtonFind = new JButton(AppConstants.MAILVIEW_FIND);
        jButtonFind.addActionListener(e -> find());
        jButtonFind.addKeyListener(new FindListener());
        //
        jPanelNorth.add(jButtonFind);
        jButtonAdd = new JButton(AppConstants.MAILVIEW_WRITE);
        jButtonAdd.addActionListener(e -> {
            //new AddView();
        });
        jPanelNorth.add(jButtonAdd);
        // update
        jButtonUpdate = new JButton(AppConstants.MAILVIEW_TEMP_UPDATE);
        jButtonUpdate.addActionListener(e -> {
            //new UpdateView();
        });
        jPanelNorth.add(jButtonUpdate);

        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(1, 1));

        // 메일 목록 가져오기
        int newMailNum = MailUtil.getMailListFromServer();

        String[][] result = MailDAO.getInstance().selectMailList("", "N");
        myTableModel = new DefaultTableModel(result, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, cr);
        initJTable(jTable, result);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int row = jTable.rowAtPoint(e.getPoint());
                    int mail_seq = Integer.parseInt(jTable.getValueAt(row, 4).toString());
                    String mail_important = jTable.getValueAt(row, 3).toString();

                    if(MailDAO.getInstance().updateMailImportant(mail_seq)) {
                        if(mail_important.equals("Y")) {
                            jTable.setValueAt("N", row, 3);
                        }
                        else {
                            jTable.setValueAt("Y", row, 3);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, AppConstants.IMPORTANT_FAIL);
                    }
                }
            }
        });

        jScrollPane = new JScrollPane(jTable);
        jPanelCenter.add(jScrollPane);

        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(jPanelCenter, BorderLayout.CENTER);

        setBounds(400, 200, 1200, 700);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        if(newMailNum > 0) {
            JOptionPane.showMessageDialog(null, newMailNum + AppConstants.NEW_MAIL);
        }
    }

    public static void initJTable(JTable jTable, String[][] result) {
        ((DefaultTableModel) jTable.getModel()).setDataVector(result, columns);
        jTable.setRowHeight(30);
        TableColumn firstColumn = jTable.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(570);
        firstColumn.setMaxWidth(570);
        firstColumn.setMinWidth(570);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(330);
        secondColumn.setMaxWidth(330);
        secondColumn.setMinWidth(330);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(150);
        thirdColumn.setMaxWidth(150);
        thirdColumn.setMinWidth(150);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(100);
        fourthColumn.setMaxWidth(100);
        fourthColumn.setMinWidth(100);
        TableColumn fifthColumn = jTable.getColumnModel().getColumn(4);
        fifthColumn.setPreferredWidth(0);
        fifthColumn.setMaxWidth(0);
        fifthColumn.setMinWidth(0);
    }

    private class FindListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                find();
            }
        }
    }

    private void find() {
        String param = condition.getText();
        if("".equals(param) || param == null) {
            initJTable(MainView.jTable, null);
            return;
        }
        //String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).queryByName(param);
        String[][] result = {{}, {}};
        condition.setText("");
        initJTable(MainView.jTable, result);
    }

}
