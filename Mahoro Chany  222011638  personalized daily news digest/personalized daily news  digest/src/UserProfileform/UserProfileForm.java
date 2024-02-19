package UserProfileform;

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

public class UserProfileForm implements ActionListener {
    JFrame frame;
    JLabel userIDLabel = new JLabel("User ID");
    JLabel usernameLabel = new JLabel("Username");
    JLabel emailLabel = new JLabel("Email");
    JLabel preferencesLabel = new JLabel("Preferences");
    JLabel readingHistoryLabel = new JLabel("Reading History");
    JLabel subscriptionStatusLabel = new JLabel("Subscription Status");

    JTextField userIDField = new JTextField();
    JTextField usernameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField preferencesField = new JTextField();
    JTextField readingHistoryField = new JTextField();
    JTextField subscriptionStatusField = new JTextField();

    JButton insertButton = new JButton("INSERT");
    JButton viewButton = new JButton("VIEW");
    JButton updateButton = new JButton("UPDATE");
    JButton deleteButton = new JButton("DELETE");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    public UserProfileForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame();
        frame.setTitle("User Profile Form");
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
        userIDLabel.setBounds(10, 10, 130, 30);
        usernameLabel.setBounds(10, 50, 130, 30);
        emailLabel.setBounds(10, 90, 130, 30);
        preferencesLabel.setBounds(10, 130, 130, 30);
        readingHistoryLabel.setBounds(10, 170, 130, 30);
        subscriptionStatusLabel.setBounds(10, 210, 130, 30);

        userIDField.setBounds(200, 10, 210, 30);
        usernameField.setBounds(200, 50, 210, 30);
        emailField.setBounds(200, 90, 210, 30);
        preferencesField.setBounds(200, 130, 210, 30);
        readingHistoryField.setBounds(200, 170, 210, 30);
        subscriptionStatusField.setBounds(200, 210, 210, 30);

        insertButton.setBounds(100, 250, 100, 30);
        viewButton.setBounds(210, 250, 100, 30);
        updateButton.setBounds(100, 290, 100, 30);
        deleteButton.setBounds(210, 290, 100, 30);

        table.setBounds(500, 10, 600, 250);

        setFontForAll();

        frame.add(userIDLabel);
        frame.add(usernameLabel);
        frame.add(emailLabel);
        frame.add(preferencesLabel);
        frame.add(readingHistoryLabel);
        frame.add(subscriptionStatusLabel);

        frame.add(userIDField);
        frame.add(usernameField);
        frame.add(emailField);
        frame.add(preferencesField);
        frame.add(readingHistoryField);
        frame.add(subscriptionStatusField);

        frame.add(insertButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(table);
    }

    private void setFontForAll() {
        Font font = new Font("Arial", Font.BOLD, 14);
        userIDLabel.setFont(font);
        usernameLabel.setFont(font);
        emailLabel.setFont(font);
        preferencesLabel.setFont(font);
        readingHistoryLabel.setFont(font);
        subscriptionStatusLabel.setFont(font);

        userIDField.setFont(font);
        usernameField.setFont(font);
        emailField.setFont(font);
        preferencesField.setFont(font);
        readingHistoryField.setFont(font);
        subscriptionStatusField.setFont(font);

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
                int userID = Integer.parseInt(userIDField.getText());
                String username = usernameField.getText();
                String email = emailField.getText();
                String preferences = preferencesField.getText();
                String readingHistory = readingHistoryField.getText();
                String subscriptionStatus = subscriptionStatusField.getText();

                UserProfile userProfile = new UserProfile(userID, username, email, preferences, readingHistory,
                        subscriptionStatus);
                userProfile.insertData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid User ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("User ID");
            model.addColumn("Username");
            model.addColumn("Email");
            model.addColumn("Preferences");
            model.addColumn("Reading History");
            model.addColumn("Subscription Status");

            ResultSet resultSet = UserProfile.viewData();
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        model.addRow(new Object[] { resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6) });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == updateButton) {
            try {
                int userID = Integer.parseInt(userIDField.getText());
                String username = usernameField.getText();
                String email = emailField.getText();
                String preferences = preferencesField.getText();
                String readingHistory = readingHistoryField.getText();
                String subscriptionStatus = subscriptionStatusField.getText();

                UserProfile userProfile = new UserProfile(userID, username, email, preferences, readingHistory,
                        subscriptionStatus);
                userProfile.updateData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid User ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            try {
                int userID = Integer.parseInt(userIDField.getText());
                UserProfile userProfile = new UserProfile();
                userProfile.setUserID(userID);
                userProfile.deleteData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid User ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        UserProfileForm userProfileForm = new UserProfileForm();
    }
}

