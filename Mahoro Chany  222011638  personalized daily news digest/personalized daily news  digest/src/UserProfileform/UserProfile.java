package UserProfileform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UserProfile {
    private int userID;
    private String username;
    private String email;
    private String preferences;
    private String readingHistory;
    private String subscriptionStatus;

    public UserProfile() {
        // Default constructor
    }

    public UserProfile(int userID, String username, String email, String preferences, String readingHistory,
            String subscriptionStatus) {
        super();
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.preferences = preferences;
        this.readingHistory = readingHistory;
        this.subscriptionStatus = subscriptionStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(String readingHistory) {
        this.readingHistory = readingHistory;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void insertData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO UserProfile (UserID, Username, Email, Preferences, ReadingHistory, SubscriptionStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.userID);
            preparedStatement.setString(2, this.username);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, this.preferences);
            preparedStatement.setString(5, this.readingHistory);
            preparedStatement.setString(6, this.subscriptionStatus);

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

        String sql = "UPDATE UserProfile SET Username=?, Email=?, Preferences=?, ReadingHistory=?, SubscriptionStatus=? WHERE UserID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.preferences);
            preparedStatement.setString(4, this.readingHistory);
            preparedStatement.setString(5, this.subscriptionStatus);
            preparedStatement.setInt(6, this.userID);

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

        String sql = "DELETE FROM UserProfile WHERE UserID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.userID);

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

        String sql = "SELECT * FROM UserProfile";

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

