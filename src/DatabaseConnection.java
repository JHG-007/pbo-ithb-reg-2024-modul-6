import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String URL = "jdbc:mysql://localhost/ktp_db";
    private static String USER = "root";
    private static String PASSWORD = "";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database!");
            return null;
        }
    }
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost/ktp_db";
        String USER = "root";
        String PASSWORD = "";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish Connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Koneksi berhasil!");
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database!");
            e.printStackTrace();
        }
    }
    
}
