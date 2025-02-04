package cosc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class datass extends javax.swing.JFrame {

    /**
     * Creates new form datass
     */
    public datass() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Replace the auto-generated code with the content from display class
        String url = "jdbc:mysql://localhost/cosc";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM register ORDER BY id DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);

            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

            if (resultSet.next()) {
                JLabel[] labels = {
                        new JLabel("ID: "),
                        new JLabel(Integer.toString(resultSet.getInt("id"))),
                        new JLabel("Username: "),
                        new JLabel(resultSet.getString("username")),
                        new JLabel("Password: "),
                        new JLabel(resultSet.getString("password")),
                        new JLabel("First Name: "),
                        new JLabel(resultSet.getString("fname")),
                        new JLabel("Last Name: "),
                        new JLabel(resultSet.getString("lname")),
                        new JLabel("Program: "),
                        new JLabel(resultSet.getString("program")),
                        new JLabel("Year and Section: "),
                        new JLabel(resultSet.getString("yrsec")),
                        new JLabel("Created At: "),
                        new JLabel(resultSet.getString("created_at"))
                };

                for (int i = 0; i < labels.length; i += 2) {
                    labels[i].setFont(labels[i].getFont().deriveFont(Font.BOLD)); // Bold font for labels
                    labels[i].setHorizontalAlignment(SwingConstants.RIGHT); // Align labels to the right
                }

                for (JLabel label : labels) {
                    panel.add(label);
                }
            } else {
                JLabel label = new JLabel("No data found!");
                panel.add(label);
            }

            JButton logoutButton = new JButton("Log In");
            logoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    LoginPage register = new LoginPage();
                    register.setVisible(true);
                }
            });

            panel.add(new JLabel());
            panel.add(logoutButton);
            add(panel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(datass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
