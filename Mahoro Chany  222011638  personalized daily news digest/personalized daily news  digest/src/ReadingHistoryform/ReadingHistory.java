package ReadingHistoryform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ReadingHistory {
    private int historyID;
    private int userID;
    private int articleID;
    private String timestamp;
    private int readingDuration;

    public ReadingHistory() {
        // Default constructor
    }

    public ReadingHistory(int historyID, int userID, int articleID, String timestamp, int readingDuration) {
        super();
        this.historyID = historyID;
        this.userID = userID;
        this.articleID = articleID;
        this.timestamp = timestamp;
        this.readingDuration = readingDuration;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getReadingDuration() {
        return readingDuration;
    }

    public void setReadingDuration(int readingDuration) {
        this.readingDuration = readingDuration;
    }

    public void insertData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO ReadingHistory (HistoryID, UserID, ArticleID, Timestamp, ReadingDuration) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.historyID);
            preparedStatement.setInt(2, this.userID);
            preparedStatement.setInt(3, this.articleID);
            preparedStatement.setString(4, this.timestamp);
            preparedStatement.setInt(5, this.readingDuration);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
                JOptionPane.showMessageDialog(null, "Data inserted successfully!", "After insert",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data!", "After insert",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "UPDATE ReadingHistory SET UserID=?, ArticleID=?, Timestamp=?, ReadingDuration=? WHERE HistoryID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.userID);
            preparedStatement.setInt(2, this.articleID);
            preparedStatement.setString(3, this.timestamp);
            preparedStatement.setInt(4, this.readingDuration);
            preparedStatement.setInt(5, this.historyID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data updated successfully!");
                JOptionPane.showMessageDialog(null, "Data updated successfully!", "After update",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to update data.");
                JOptionPane.showMessageDialog(null, "Failed to update data!", "After update",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "DELETE FROM ReadingHistory WHERE HistoryID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.historyID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully!");
                JOptionPane.showMessageDialog(null, "Data deleted successfully!", "After delete",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to delete data.");
                JOptionPane.showMessageDialog(null, "Failed to delete data!", "After delete",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet viewData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "SELECT * FROM ReadingHistory";

        try {
            Connection con = DriverManager.getConnection(host, user, password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

	