package NewsArticleform;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import javax.swing.JOptionPane;

	public class NewsArticle {
	    private int articleID;
	    private String title;
	    private String author;
	    private String publicationDate;
	    private String content;
	    private String tags;
	    private String source;

	    public NewsArticle() {
	        // Default constructor
	    }

	    public NewsArticle(int articleID, String title, String author, String publicationDate,
	            String content, String tags, String source) {
	        super();
	        this.articleID = articleID;
	        this.title = title;
	        this.author = author;
	        this.publicationDate = publicationDate;
	        this.content = content;
	        this.tags = tags;
	        this.source = source;
	    }

	    public int getArticleID() {
	        return articleID;
	    }

	    public void setArticleID(int articleID) {
	        this.articleID = articleID;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    public String getPublicationDate() {
	        return publicationDate;
	    }

	    public void setPublicationDate(String publicationDate) {
	        this.publicationDate = publicationDate;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public String getTags() {
	        return tags;
	    }

	    public void setTags(String tags) {
	        this.tags = tags;
	    }

	    public String getSource() {
	        return source;
	    }

	    public void setSource(String source) {
	        this.source = source;
	    }

	    public void insertData() {
	        String host = "jdbc:mysql://localhost/personalized daily news digest";
	        String user = "root";
	        String password = "";

	        String sql = "INSERT INTO NewsArticle (ArticleID, Title, Author, PublicationDate, Content, Tags, Source) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

	            preparedStatement.setInt(1, this.articleID);
	            preparedStatement.setString(2, this.title);
	            preparedStatement.setString(3, this.author);
	            preparedStatement.setString(4, this.publicationDate);
	            preparedStatement.setString(5, this.content);
	            preparedStatement.setString(6, this.tags);
	            preparedStatement.setString(7, this.source);

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

	        String sql = "UPDATE NewsArticle SET Title=?, Author=?, PublicationDate=?, Content=?, Tags=?, Source=? WHERE ArticleID=?";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

	            preparedStatement.setString(1, this.title);
	            preparedStatement.setString(2, this.author);
	            preparedStatement.setString(3, this.publicationDate);
	            preparedStatement.setString(4, this.content);
	            preparedStatement.setString(5, this.tags);
	            preparedStatement.setString(6, this.source);
	            preparedStatement.setInt(7, this.articleID);

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

	        String sql = "DELETE FROM NewsArticle WHERE ArticleID=?";

	        try (Connection con = DriverManager.getConnection(host, user, password);
	                PreparedStatement preparedStatement = con.prepareStatement(sql);) {

	            preparedStatement.setInt(1, this.articleID);

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

	        String sql = "SELECT * FROM NewsArticle";

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


