
package menu;

import javax.swing.*;

import NewsArticleform.NewsArticleForm;
import ReadingHistoryform.ReadingHistoryForm;
import UserProfileform.UserProfileForm;
import digestform.DigestConfigurationForm;
import notificationlogform.NotificationLogForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class menu extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem userProfileItem;
    private JMenuItem readingHistoryItem;
    private JMenuItem notificationLogItem;
    private JMenuItem newsArticleItem;
    private JMenuItem digestConfigurationItem;
    private JMenuItem settingsItem;
    private JMenuItem logoutItem;
    private String loggedInUser;

    public menu(String username) {
        this.loggedInUser = username;
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new JMenuBar();

        // Create home menu
        homeMenu = new JMenu("Home");

        // Create menu items
        userProfileItem = new JMenuItem("User Profile");
        userProfileItem.addActionListener(this);
        readingHistoryItem = new JMenuItem("Reading History");
        readingHistoryItem.addActionListener(this);
        notificationLogItem = new JMenuItem("Notification Log");
        notificationLogItem.addActionListener(this);
        newsArticleItem = new JMenuItem("News Article");
        newsArticleItem.addActionListener(this);
        digestConfigurationItem = new JMenuItem("Digest Configuration");
        digestConfigurationItem.addActionListener(this);
        settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(this);
        logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(this);

        // Add menu items to home menu
        homeMenu.add(userProfileItem);
        homeMenu.add(readingHistoryItem);
        homeMenu.add(notificationLogItem);
        homeMenu.add(newsArticleItem);
        homeMenu.add(digestConfigurationItem);
        homeMenu.addSeparator();
        homeMenu.add(settingsItem);
        homeMenu.addSeparator();
        homeMenu.add(logoutItem);

        // Add home menu to menu bar
        menuBar.add(homeMenu);

        // Set menu bar to frame
        setJMenuBar(menuBar);

        // Initialize dashboard panel with background image
        JPanel dashboardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\mahoro chany\\Desktop\\New folder\\Bluesky.jpg");
                // Draw the image
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        dashboardPanel.setLayout(new BorderLayout());

        // Add components to dashboard panel
        JLabel titleLabel = new JLabel("Welcome " + loggedInUser + " to personalized daily news digest");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dashboardPanel.add(titleLabel, BorderLayout.CENTER);

        // Add dashboard panel to frame
        add(dashboardPanel);

        setVisible(true);
    }

    public void actionPerformed1(ActionEvent e) {
        // Action event handling logic here
    }

    public static void main1(String[] args) {
        SwingUtilities.invokeLater(() -> new menu("loyal customer"));
    }


@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == userProfileItem) {
        JOptionPane.showMessageDialog(this, "Opening User Profile Form...");
        new UserProfileForm();
    } else if (e.getSource() == readingHistoryItem) {
        JOptionPane.showMessageDialog(this, "Opening Reading History Form...");
        new ReadingHistoryForm();
    } else if (e.getSource() == notificationLogItem) {
        JOptionPane.showMessageDialog(this, "Opening Notification Log Form...");
        new NotificationLogForm();
    } else if (e.getSource() == newsArticleItem) {
        JOptionPane.showMessageDialog(this, "Opening News Article Form...");
        new NewsArticleForm();
    } else if (e.getSource() == digestConfigurationItem) {
        JOptionPane.showMessageDialog(this, "Opening Digest Configuration Form...");
        new DigestConfigurationForm();
    } else if (e.getSource() == settingsItem) {
        String newUsername = JOptionPane.showInputDialog(this, "Enter new username:");
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
        JOptionPane.showMessageDialog(this, "User registered successfully: " + newUsername);
    } else if (e.getSource() == logoutItem) {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new menu("loyal customer"));
}
}

