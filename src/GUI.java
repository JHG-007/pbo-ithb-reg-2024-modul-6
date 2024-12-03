import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GUI extends JFrame {
    JTextField NIK, nama, ttl, alamat, RtRw, kelDesa, kecamatan, expDate, kotaPembuatan;
    JComboBox<String> agama, status;
    JRadioButton pria, wanita, wna, wni;
    JButton insertButton, backButton;

    public void setData(String nik, String nama, String ttl, String alamat, String rtRw, String kelDesa, String kecamatan, String agama, String status) {
        this.NIK.setText(nik);              
        this.nama.setText(nama);             
        this.ttl.setText(ttl);               
        this.alamat.setText(alamat);         
        this.RtRw.setText(rtRw);            
        this.kelDesa.setText(kelDesa);       
        this.kecamatan.setText(kecamatan);   
        this.agama.setSelectedItem(agama);  
        this.status.setSelectedItem(status);
    }

    public GUI() {
        setTitle("Form Perekaman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(15, 2, 5, 5));

        // Input fields
        formPanel.add(new JLabel("NIK:"));
        NIK = new JTextField();
        formPanel.add(NIK);

        formPanel.add(new JLabel("Nama:"));
        nama = new JTextField();
        formPanel.add(nama);

        formPanel.add(new JLabel("Tempat Lahir:"));
        ttl = new JTextField();
        formPanel.add(ttl);

        formPanel.add(new JLabel("Alamat:"));
        alamat = new JTextField();
        formPanel.add(alamat);

        formPanel.add(new JLabel("RT/RW:"));
        RtRw = new JTextField();
        formPanel.add(RtRw);

        formPanel.add(new JLabel("Kel/Desa:"));
        kelDesa = new JTextField();
        formPanel.add(kelDesa);

        formPanel.add(new JLabel("Kecamatan:"));
        kecamatan = new JTextField();
        formPanel.add(kecamatan);

        formPanel.add(new JLabel("Agama:"));
        agama = new JComboBox<>(new String[] { "Islam", "Kristen","Katolik", "Hindu", "Budha", "Konghucu" });
        formPanel.add(agama);

        formPanel.add(new JLabel("Status Perkawinan:"));
        status = new JComboBox<>(new String[] { "Belum Menikah", "Menikah", "Janda/Duda" });
        formPanel.add(status);

        // Buttons
        insertButton = new JButton("Insert Data");
        backButton = new JButton("Back to Main Menu");
        formPanel.add(insertButton);
        formPanel.add(backButton);

        insertButton.addActionListener(e -> insertData());
        backButton.addActionListener(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            dispose();
        });

        add(formPanel, BorderLayout.CENTER);
    }

    private void insertData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO data_ktp (NIK, nama, tempat_lahir, alamat, rt_rw, kel_desa, kecamatan, agama, status_perkawinan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, NIK.getText());
            ps.setString(2, nama.getText());
            ps.setString(3, ttl.getText());
            ps.setString(4, alamat.getText());
            ps.setString(5, RtRw.getText());
            ps.setString(6, kelDesa.getText());
            ps.setString(7, kecamatan.getText());
            ps.setString(8, agama.getSelectedItem().toString());
            ps.setString(9, status.getSelectedItem().toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
        }
    }
}
