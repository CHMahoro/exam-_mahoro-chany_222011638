package digestform;



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

import digestform.DigestConfiguration;

public class DigestConfigurationForm implements ActionListener {
    JFrame frame;
    JLabel digestIDLabel = new JLabel("Digest ID");
    JLabel userIDLabel = new JLabel("User ID");
    JLabel timeOfDeliveryLabel = new JLabel("Time of Delivery");
    JLabel preferredContentTypesLabel = new JLabel("Preferred Content Types");
    JLabel languagePreferencesLabel = new JLabel("Language Preferences");

    JTextField digestIDField = new JTextField();
    JTextField userIDField = new JTextField();
    JTextField timeOfDeliveryField = new JTextField();
    JTextField preferredContentTypesField = new JTextField();
    JTextField languagePreferencesField = new JTextField();

    JButton insertButton = new JButton("INSERT");
    JButton viewButton = new JButton("VIEW");
    JButton updateButton = new JButton("UPDATE");
    JButton deleteButton = new JButton("DELETE");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    public DigestConfigurationForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame();
        frame.setTitle("Digest Configuration Form");
        frame.setBounds(0, 0, screenWidth / 2, screenHeight / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.cyan);
        frame.getContentPane().setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);

        setLocationAndSize();
        addActionListeners();
    }

    private void setLocationAndSize() {
        digestIDLabel.setBounds(10, 10, 200, 30);
        userIDLabel.setBounds(10, 50, 200, 30);
        timeOfDeliveryLabel.setBounds(10, 90, 200, 30);
        preferredContentTypesLabel.setBounds(10, 130, 250, 30);
        languagePreferencesLabel.setBounds(10, 170, 250, 30);

        digestIDField.setBounds(250, 10, 210, 30);
        userIDField.setBounds(250, 50, 210, 30);
        timeOfDeliveryField.setBounds(250, 90, 210, 30);
        preferredContentTypesField.setBounds(250, 130, 210, 30);
        languagePreferencesField.setBounds(250, 170, 210, 30);

        insertButton.setBounds(100, 210, 100, 30);
        viewButton.setBounds(210, 210, 100, 30);
        updateButton.setBounds(100, 250, 100, 30);
        deleteButton.setBounds(210, 250, 100, 30);

        table.setBounds(600, 10, 600, 250);

        setFontForAll();

        frame.add(digestIDLabel);
        frame.add(userIDLabel);
        frame.add(timeOfDeliveryLabel);
        frame.add(preferredContentTypesLabel);
        frame.add(languagePreferencesLabel);

        frame.add(digestIDField);
        frame.add(userIDField);
        frame.add(timeOfDeliveryField);
        frame.add(preferredContentTypesField);
        frame.add(languagePreferencesField);

        frame.add(insertButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(table);
    }

    private void setFontForAll() {
        Font font = new Font("Georgia", Font.BOLD, 18);
        digestIDLabel.setFont(font);
        userIDLabel.setFont(font);
        timeOfDeliveryLabel.setFont(font);
        preferredContentTypesLabel.setFont(font);
        languagePreferencesLabel.setFont(font);

        digestIDField.setFont(font);
        userIDField.setFont(font);
        timeOfDeliveryField.setFont(font);
        preferredContentTypesField.setFont(font);
        languagePreferencesField.setFont(font);

        Font italicFont = new Font("Courier New", Font.ITALIC, 10);
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
                int digestID = Integer.parseInt(digestIDField.getText());
                String userID = userIDField.getText();
                String timeOfDelivery = timeOfDeliveryField.getText();
                String preferredContentTypes = preferredContentTypesField.getText();
                String languagePreferences = languagePreferencesField.getText();

                DigestConfiguration digestConfiguration = new DigestConfiguration(digestID, userID, timeOfDelivery,
                        preferredContentTypes, languagePreferences);
                digestConfiguration.insertData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for Digest ID and User ID!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("Digest ID");
            model.addColumn("User ID");
            model.addColumn("Time of Delivery");
            model.addColumn("Preferred Content Types");
            model.addColumn("Language Preferences");

            ResultSet resultSet = DigestConfiguration.viewData();
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        model.addRow(new Object[] { resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                                resultSet.getString(4), resultSet.getString(5) });
                    }
            } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == updateButton) {
            try {
                int digestID = Integer.parseInt(digestIDField.getText());
                String timeOfDelivery = timeOfDeliveryField.getText();
                String preferredContentTypes = preferredContentTypesField.getText();
                String languagePreferences = languagePreferencesField.getText();

                DigestConfiguration digestConfiguration = new DigestConfiguration();
                digestConfiguration.setDigestID(digestID);
                digestConfiguration.setTimeOfDelivery(timeOfDelivery);
                digestConfiguration.setPreferredContentTypes(preferredContentTypes);
                digestConfiguration.setLanguagePreferences(languagePreferences);

                digestConfiguration.updateData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Digest ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            try {
                int digestID = Integer.parseInt(digestIDField.getText());
                DigestConfiguration digestConfiguration = new DigestConfiguration();
                digestConfiguration.setDigestID(digestID);
                digestConfiguration.deleteData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Digest ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        DigestConfigurationForm digestConfigurationForm = new DigestConfigurationForm();
    }
}


