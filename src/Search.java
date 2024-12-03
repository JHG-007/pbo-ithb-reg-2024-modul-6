import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends JFrame {
    private JTextField txtNIK;
    private JButton btnCari;

    public Search() {
        setTitle("Pencarian Data");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        txtNIK = new JTextField();
        btnCari = new JButton("Cari");

        btnCari.addActionListener(e -> searchData());

        add(new JLabel("Masukkan NIK:"));
        add(txtNIK);
        add(btnCari);
    }

    void searchData() {
        String nik = txtNIK.getText();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM data_ktp WHERE NIK = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nik);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GUI perekaman = new GUI();
                perekaman.setData(
                    rs.getString("NIK"),
                    rs.getString("nama"),
                    rs.getString("tempat_lahir"),
                    rs.getString("alamat"),
                    rs.getString("rt_rw"),
                    rs.getString("kel_desa"),
                    rs.getString("kecamatan"),
                    rs.getString("agama"),
                    rs.getString("status_perkawinan")
                );
                perekaman.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
        }
    }
}
