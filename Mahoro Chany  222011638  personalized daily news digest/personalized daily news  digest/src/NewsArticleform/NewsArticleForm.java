package NewsArticleform;

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

public class NewsArticleForm implements ActionListener {
    JFrame frame;
    JLabel articleIDLabel = new JLabel("Article ID");
    JLabel titleLabel = new JLabel("Title");
    JLabel authorLabel = new JLabel("Author");
    JLabel publicationDateLabel = new JLabel("Publication Date");
    JLabel contentLabel = new JLabel("Content");
    JLabel tagsLabel = new JLabel("Tags");
    JLabel sourceLabel = new JLabel("Source");

    JTextField articleIDField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();
    JTextField publicationDateField = new JTextField();
    JTextField contentField = new JTextField();
    JTextField tagsField = new JTextField();
    JTextField sourceField = new JTextField();

    JButton insertButton = new JButton("INSERT");
    JButton viewButton = new JButton("VIEW");
    JButton updateButton = new JButton("UPDATE");
    JButton deleteButton = new JButton("DELETE");

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    public NewsArticleForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame();
        frame.setTitle("News Article Form");
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
        articleIDLabel.setBounds(10, 10, 130, 30);
        titleLabel.setBounds(10, 50, 130, 30);
        authorLabel.setBounds(10, 90, 130, 30);
        publicationDateLabel.setBounds(10, 130, 130, 30);
        contentLabel.setBounds(10, 170, 130, 30);
        tagsLabel.setBounds(10, 210, 130, 30);
        sourceLabel.setBounds(10, 250, 130, 30);

        articleIDField.setBounds(200, 10, 210, 30);
        titleField.setBounds(200, 50, 210, 30);
        authorField.setBounds(200, 90, 210, 30);
        publicationDateField.setBounds(200, 130, 210, 30);
        contentField.setBounds(200, 170, 210, 30);
        tagsField.setBounds(200, 210, 210, 30);
        sourceField.setBounds(200, 250, 210, 30);

        insertButton.setBounds(100, 290, 100, 30);
        viewButton.setBounds(210, 290, 100, 30);
        updateButton.setBounds(100, 330, 100, 30);
        deleteButton.setBounds(210, 330, 100, 30);

        table.setBounds(500, 10, 600, 250);

        setFontForAll();

        frame.add(articleIDLabel);
        frame.add(titleLabel);
        frame.add(authorLabel);
        frame.add(publicationDateLabel);
        frame.add(contentLabel);
        frame.add(tagsLabel);
        frame.add(sourceLabel);

        frame.add(articleIDField);
        frame.add(titleField);
        frame.add(authorField);
        frame.add(publicationDateField);
        frame.add(contentField);
        frame.add(tagsField);
        frame.add(sourceField);

        frame.add(insertButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(table);
    }

    private void setFontForAll() {
        Font font = new Font("Arial", Font.BOLD, 14);
        articleIDLabel.setFont(font);
        titleLabel.setFont(font);
        authorLabel.setFont(font);
        publicationDateLabel.setFont(font);
        contentLabel.setFont(font);
        tagsLabel.setFont(font);
        sourceLabel.setFont(font);

        articleIDField.setFont(font);
        titleField.setFont(font);
        authorField.setFont(font);
        publicationDateField.setFont(font);
        contentField.setFont(font);
        tagsField.setFont(font);
        sourceField.setFont(font);

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
                int articleID = Integer.parseInt(articleIDField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String publicationDate = publicationDateField.getText();
                String content = contentField.getText();
                String tags = tagsField.getText();
                String source = sourceField.getText();

                NewsArticle newsArticle = new NewsArticle(articleID, title, author, publicationDate,
                        content, tags, source);
                newsArticle.insertData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Article ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            model.setColumnCount(0);
            model.setRowCount(0);
            model.addColumn("Article ID");
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Publication Date");
            model.addColumn("Content");
            model.addColumn("Tags");
            model.addColumn("Source");

            ResultSet resultSet = NewsArticle.viewData();
            if (resultSet != null) {
                try {
                    while (resultSet.next()) {
                        model.addRow(new Object[] { resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6), resultSet.getString(7) });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == updateButton) {
            try {
                int articleID = Integer.parseInt(articleIDField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String publicationDate = publicationDateField.getText();
                String content = contentField.getText();
                String tags = tagsField.getText();
                String source = sourceField.getText();

                NewsArticle newsArticle = new NewsArticle(articleID, title, author, publicationDate,
                        content, tags, source);
                newsArticle.updateData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Article ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            try {
                int articleID = Integer.parseInt(articleIDField.getText());
                NewsArticle newsArticle = new NewsArticle();
                newsArticle.setArticleID(articleID);
                newsArticle.deleteData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Article ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        NewsArticleForm newsArticleForm = new NewsArticleForm();
    }
}


