package cosc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class WelcomeUser extends JFrame {
    private JLabel welcomeLabel;
    private JLabel courseLabel;
    private JLabel yearSecLabel;
    private String username;

    public WelcomeUser(String username, String program, String yrsec) {
        this.username = username;
        initComponents();
        fetchUserDataFromDatabase(username, program, yrsec);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        welcomeLabel = new JLabel();
        courseLabel = new JLabel();
        yearSecLabel = new JLabel();
        JButton evaluate = new JButton();
        JButton exit = new JButton();

        welcomeLabel.setFont(new java.awt.Font("Times New Roman", 0, 30));
        welcomeLabel.setText("WELCOME, " + username + "!");

        courseLabel.setFont(new java.awt.Font("Tahoma", 0, 15));
        courseLabel.setText("COURSE:");

        yearSecLabel.setFont(new java.awt.Font("Tahoma", 0, 15));
        yearSecLabel.setText("YEAR & SECTION:");

        evaluate.setBackground(new java.awt.Color(0, 153, 51));
        evaluate.setForeground(new java.awt.Color(255, 255, 255));
        evaluate.setText("EVALUATE YOUR GRADE NOW!");
        evaluate.addActionListener((java.awt.event.ActionEvent evt) -> {
            evaluateActionPerformed(evt);
        });

        exit.setBackground(new java.awt.Color(255, 0, 0));
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("LOG OUT");
        exit.addActionListener((java.awt.event.ActionEvent evt) -> {
            exitActionPerformed(evt);
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(385, Short.MAX_VALUE)
                                .addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
                                .addGap(359, 359, 359))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yearSecLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(247, 247, 247))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(evaluate, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exit, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(yearSecLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addComponent(evaluate, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(exit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void evaluateActionPerformed(ActionEvent evt) {
        MainPage home = new MainPage();
        home.setVisible(true);
        dispose();
    }

    private void exitActionPerformed(ActionEvent evt) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        dispose();
    }

    private void fetchUserDataFromDatabase(String username, String program, String yrsec) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://localhost/cosc";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String sql = "SELECT username, program, yrsec FROM register WHERE username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String usernameFromDatabase = resultSet.getString("username");
                String programFromDatabase = resultSet.getString("program");
                String yrsecFromDatabase = resultSet.getString("yrsec");

                welcomeLabel.setText("WELCOME, " + usernameFromDatabase + "!");
                courseLabel.setText("COURSE: " + programFromDatabase);
                yearSecLabel.setText("YEAR & SECTION: " + yrsecFromDatabase);
            } else {
                welcomeLabel.setText("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Set the look and feel of the application
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        java.awt.EventQueue.invokeLater(() -> {
            new WelcomeUser("username", "program", "yrsec").setVisible(true);
        });
    }
}
