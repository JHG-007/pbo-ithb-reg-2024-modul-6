import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends JFrame {
    private JTextField searchNIK;
    private JButton searchButton;

    public Search() {
        setTitle("Search Data Penduduk");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        searchNIK = new JTextField();
        searchButton = new JButton("Cari");

        add(new JLabel("Masukkan NIK:"));
        add(searchNIK);
        add(searchButton);

        // Tombol Cari
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String query = "SELECT * FROM data_ktp WHERE NIK = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, searchNIK.getText());
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        // Jika ditemukan, buka halaman Perekaman dengan data terisi
                        Search search = new Search();
                        search.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }
}
