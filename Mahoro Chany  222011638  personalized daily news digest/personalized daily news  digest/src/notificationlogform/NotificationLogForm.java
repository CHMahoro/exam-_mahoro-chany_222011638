package notificationlogform;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NotificationLogForm implements ActionListener {
    JFrame frame;
    JLabel logIDLabel = new JLabel("Log ID");
    JLabel userIDLabel = new JLabel("User ID");
    JLabel notificationTypeLabel = new JLabel("Notification Type");
    JLabel timestampLabel = new JLabel("Timestamp");
    JLabel statusLabel = new JLabel("Status");

    JTextField logIDField = new JTextField();
    JTextField userIDField = new JTextField();
    JTextField notificationTypeField = new JTextField();
    JTextField timestampField = new JTextField();
    JTextField statusField = new JTextField();

    JButton insertButton = new JButton("INSERT");
    JButton viewButton = new JButton("VIEW");
    JButton updateButton = new JButton("UPDATE");
    JButton deleteButton = new JButton("DELETE");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    public NotificationLogForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame();
        frame.setTitle("Notification Log Form");
        frame.setBounds(0, 0, screenWidth / 2, screenHeight / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.getContentPane().setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);

        setLocationAndSize();
        addActionListeners();
    }

    private void setLocationAndSize() {
        logIDLabel.setBounds(10, 10, 130, 30);
        userIDLabel.setBounds(10, 50, 130, 30);
        notificationTypeLabel.setBounds(10, 90, 130, 30);
        timestampLabel.setBounds(10, 130, 130, 30);
        statusLabel.setBounds(10, 170, 130, 30);

        logIDField.setBounds(200, 10, 210, 30);
        userIDField.setBounds(200, 50, 210, 30);
        notificationTypeField.setBounds(200, 90, 210, 30);
        timestampField.setBounds(200, 130, 210, 30);
        statusField.setBounds(200, 170, 210, 30);

        insertButton.setBounds(100, 210, 100, 30);
        viewButton.setBounds(210, 210, 100, 30);
        updateButton.setBounds(100, 250, 100, 30);
        deleteButton.setBounds(210, 250, 100, 30);

        table.setBounds(500, 10, 600, 250);

        setFontForAll();

        frame.add(logIDLabel);
        frame.add(userIDLabel);
        frame.add(notificationTypeLabel);
        frame.add(timestampLabel);
        frame.add(statusLabel);

        frame.add(logIDField);
        frame.add(userIDField);
        frame.add(notificationTypeField);
        frame.add(timestampField);
        frame.add(statusField);

        frame.add(insertButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(table);
    }

    private void setFontForAll() {
        Font font = new Font("Arial", Font.BOLD, 14);
        logIDLabel.setFont(font);
        userIDLabel.setFont(font);
        notificationTypeLabel.setFont(font);
        timestampLabel.setFont(font);
        statusLabel.setFont(font);

        logIDField.setFont(font);
        userIDField.setFont(font);
        notificationTypeField.setFont(font);
        timestampField.setFont(font);
        statusField.setFont(font);

        Font italicFont = new Font("Arial", Font.ITALIC, 12);
        insertButton.setFont(italicFont);
        viewButton.setFont(italicFont);
        updateButton.setFont(italicFont);
        deleteButton.setFont(italicFont);
    }

    private void addActionListeners() {
        insertButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            try {
                int logID = Integer.parseInt(logIDField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                String notificationType = notificationTypeField.getText();
                String timestamp = timestampField.getText();
                String status = statusField.getText();

                NotificationLog notificationLog = new NotificationLog(logID, userID, notificationType, timestamp,
                        status);
                notificationLog.insertData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid Log ID and User ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("Log ID");
            model.addColumn("User ID");
            model.addColumn("Notification Type");
            model.addColumn("Timestamp");
            model.addColumn("Status");

            ResultSet resultSet = NotificationLog.viewData();
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        model.addRow(new Object[] { resultSet.getInt(1), resultSet.getInt(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5) });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == updateButton) {
            try {
                int logID = Integer.parseInt(logIDField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                String notificationType = notificationTypeField.getText();
                String timestamp = timestampField.getText();
                String status = statusField.getText();

                NotificationLog notificationLog = new NotificationLog(logID, userID, notificationType, timestamp,
                        status);
                notificationLog.updateData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid Log ID and User ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            try {
                int logID = Integer.parseInt(logIDField.getText());
                NotificationLog notificationLog = new NotificationLog();
                notificationLog.setLogID(logID);
                notificationLog.deleteData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Log ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        NotificationLogForm notificationLogForm = new NotificationLogForm();
    }
}

