package ReadingHistoryform;



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

public class ReadingHistoryForm implements ActionListener {
    JFrame frame;
    JLabel historyIDLabel = new JLabel("History ID");
    JLabel userIDLabel = new JLabel("User ID");
    JLabel articleIDLabel = new JLabel("Article ID");
    JLabel timestampLabel = new JLabel("Timestamp");
    JLabel readingDurationLabel = new JLabel("Reading Duration");

    JTextField historyIDField = new JTextField();
    JTextField userIDField = new JTextField();
    JTextField articleIDField = new JTextField();
    JTextField timestampField = new JTextField();
    JTextField readingDurationField = new JTextField();

    JButton insertButton = new JButton("INSERT");
    JButton viewButton = new JButton("VIEW");
    JButton updateButton = new JButton("UPDATE");
    JButton deleteButton = new JButton("DELETE");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    public ReadingHistoryForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame();
        frame.setTitle("Reading History Form");
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
        historyIDLabel.setBounds(10, 10, 130, 30);
        userIDLabel.setBounds(10, 50, 130, 30);
        articleIDLabel.setBounds(10, 90, 130, 30);
        timestampLabel.setBounds(10, 130, 130, 30);
        readingDurationLabel.setBounds(10, 170, 130, 30);

        historyIDField.setBounds(200, 10, 210, 30);
        userIDField.setBounds(200, 50, 210, 30);
        articleIDField.setBounds(200, 90, 210, 30);
        timestampField.setBounds(200, 130, 210, 30);
        readingDurationField.setBounds(200, 170, 210, 30);

        insertButton.setBounds(100, 250, 100, 30);
        viewButton.setBounds(210, 250, 100, 30);
        updateButton.setBounds(100, 290, 100, 30);
        deleteButton.setBounds(210, 290, 100, 30);

        table.setBounds(500, 10, 600, 250);

        setFontForAll();

        frame.add(historyIDLabel);
        frame.add(userIDLabel);
        frame.add(articleIDLabel);
        frame.add(timestampLabel);
        frame.add(readingDurationLabel);

        frame.add(historyIDField);
        frame.add(userIDField);
        frame.add(articleIDField);
        frame.add(timestampField);
        frame.add(readingDurationField);

        frame.add(insertButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(table);
    }

    private void setFontForAll() {
        Font font = new Font("Arial", Font.BOLD, 14);
        historyIDLabel.setFont(font);
        userIDLabel.setFont(font);
        articleIDLabel.setFont(font);
        timestampLabel.setFont(font);
        readingDurationLabel.setFont(font);

        historyIDField.setFont(font);
        userIDField.setFont(font);
        articleIDField.setFont(font);
        timestampField.setFont(font);
        readingDurationField.setFont(font);

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
                int historyID = Integer.parseInt(historyIDField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                int articleID = Integer.parseInt(articleIDField.getText());
                String timestamp = timestampField.getText();
                int readingDuration = Integer.parseInt(readingDurationField.getText());

                ReadingHistory readingHistory = new ReadingHistory(historyID, userID, articleID, timestamp, readingDuration);
                readingHistory.insertData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("History ID");
            model.addColumn("User ID");
            model.addColumn("Article ID");
            model.addColumn("Timestamp");
            model.addColumn("Reading Duration");

            ResultSet resultSet = ReadingHistory.viewData();
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        model.addRow(new Object[] { resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                                resultSet.getString(4), resultSet.getInt(5) });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == updateButton) {
            try {
                int historyID = Integer.parseInt(historyIDField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                int articleID = Integer.parseInt(articleIDField.getText());
                String timestamp = timestampField.getText();
                int readingDuration = Integer.parseInt(readingDurationField.getText());

                ReadingHistory readingHistory = new ReadingHistory(historyID, userID, articleID, timestamp, readingDuration);
                readingHistory.updateData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            try {
                int historyID = Integer.parseInt(historyIDField.getText());
                ReadingHistory readingHistory = new ReadingHistory();
                readingHistory.setHistoryID(historyID);
                readingHistory.deleteData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        ReadingHistoryForm readingHistoryForm = new ReadingHistoryForm();
    }
}


