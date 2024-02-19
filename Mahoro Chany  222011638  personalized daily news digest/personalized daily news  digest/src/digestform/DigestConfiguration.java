package digestform;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DigestConfiguration {
    private int digestID;
    private String userID;
    private String timeOfDelivery;
    private String preferredContentTypes;
    private String languagePreferences;

    public DigestConfiguration() {
        // Default constructor
    }

    public DigestConfiguration(int digestID, String userID, String timeOfDelivery, String preferredContentTypes,
            String languagePreferences) {
        super();
        this.digestID = digestID;
        this.userID = userID;
        this.timeOfDelivery = timeOfDelivery;
        this.preferredContentTypes = preferredContentTypes;
        this.languagePreferences = languagePreferences;
    }

    public int getDigestID() {
        return digestID;
    }

    public void setDigestID(int digestID) {
        this.digestID = digestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(String timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public String getPreferredContentTypes() {
        return preferredContentTypes;
    }

    public void setPreferredContentTypes(String preferredContentTypes) {
        this.preferredContentTypes = preferredContentTypes;
    }

    public String getLanguagePreferences() {
        return languagePreferences;
    }

    public void setLanguagePreferences(String languagePreferences) {
        this.languagePreferences = languagePreferences;
    }

    public void insertData() {
        String host = "jdbc:mysql://localhost/personalized daily news digest";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO DigestConfiguration (DigestID, UserID, TimeOfDelivery, PreferredContentTypes, LanguagePreferences) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.digestID);
            preparedStatement.setString(2, this.userID);
            preparedStatement.setString(3, this.timeOfDelivery);
            preparedStatement.setString(4, this.preferredContentTypes);
            preparedStatement.setString(5, this.languagePreferences);

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

        String sql = "UPDATE DigestConfiguration SET TimeOfDelivery=?, PreferredContentTypes=?, LanguagePreferences=? WHERE DigestID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setString(1, this.timeOfDelivery);
            preparedStatement.setString(2, this.preferredContentTypes);
            preparedStatement.setString(3, this.languagePreferences);
            preparedStatement.setInt(4, this.digestID);

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

        String sql = "DELETE FROM DigestConfiguration WHERE DigestID=?";

        try (Connection con = DriverManager.getConnection(host, user, password);
                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

            preparedStatement.setInt(1, this.digestID);

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

        String sql = "SELECT * FROM DigestConfiguration";

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


