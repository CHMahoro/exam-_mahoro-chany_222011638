package notificationlogform;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import javax.swing.JOptionPane;

	public class NotificationLog {
	    private int logID;
	    private int userID;
	    private String notificationType;
	    private String timestamp;
	    private String status;

	    public NotificationLog() {
	        // Default constructor
	    }

	    public NotificationLog(int logID, int userID, String notificationType, String timestamp, String status) {
	        super();
	        this.logID = logID;
	        this.userID = userID;
	        this.notificationType = notificationType;
	        this.timestamp = timestamp;
	        this.status = status;
	    }

	    public int getLogID() {
	        return logID;
	    }

	    public void setLogID(int logID) {
	        this.logID = logID;
	    }

	    public int getUserID() {
	        return userID;
	    }

	    public void setUserID(int userID) {
	        this.userID = userID;
	    }

	    public String getNotificationType() {
	        return notificationType;
	    }

	    public void setNotificationType(String notificationType) {
	        this.notificationType = notificationType;
	    }

	    public String getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(String timestamp) {
	        this.timestamp = timestamp;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public void insertData() {
	        String host = "jdbc:mysql://localhost/personalized daily news digest";
	        String user = "root";
	        String password = "";

	        String sql = "INSERT INTO NotificationLog (LogID, UserID, NotificationType, Timestamp, Status) VALUES (?, ?, ?, ?, ?)";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	            preparedStatement.setInt(1, this.logID);
	            preparedStatement.setInt(2, this.userID);
	            preparedStatement.setString(3, this.notificationType);
	            preparedStatement.setString(4, this.timestamp);
	            preparedStatement.setString(5, this.status);

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

	        String sql = "UPDATE NotificationLog SET UserID=?, NotificationType=?, Timestamp=?, Status=? WHERE LogID=?";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	            preparedStatement.setInt(1, this.userID);
	            preparedStatement.setString(2, this.notificationType);
	            preparedStatement.setString(3, this.timestamp);
	            preparedStatement.setString(4, this.status);
	            preparedStatement.setInt(5, this.logID);

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

	        String sql = "DELETE FROM NotificationLog WHERE LogID=?";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	            preparedStatement.setInt(1, this.logID);

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

	        String sql = "SELECT * FROM NotificationLog";

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

