import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Record extends JFrame {
    private JTextField NIK, nama, tempatLahir, alamat, rtRw, kelurahan, kecamatan, kotaPembuatan;
    private JComboBox<String> golonganDarah, agama, statusPerkawinan;
    private JRadioButton pria, wanita, wni, wna;
    private JSpinner tanggalLahir, berlakuHingga, tanggalPembuatan;
    private JButton submitButton, backButton;

    public Record() {
        setTitle("Record Data Penduduk");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(20, 2, 5, 5));

        // NIK
        add(new JLabel("NIK:"));
        NIK = new JTextField();
        add(NIK);

        // Nama
        add(new JLabel("Nama:"));
        nama = new JTextField();
        add(nama);

        // Tempat Lahir
        add(new JLabel("Tempat Lahir:"));
        tempatLahir = new JTextField();
        add(tempatLahir);

        // Tanggal Lahir
        add(new JLabel("Tanggal Lahir:"));
        tanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(tanggalLahir, "yyyy-MM-dd");
        tanggalLahir.setEditor(dateEditor1);
        add(tanggalLahir);

        // Jenis Kelamin
        add(new JLabel("Jenis Kelamin:"));
        pria = new JRadioButton("Pria");
        wanita = new JRadioButton("Wanita");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(pria);
        genderGroup.add(wanita);
        JPanel genderPanel = new JPanel();
        genderPanel.add(pria);
        genderPanel.add(wanita);
        add(genderPanel);

        // Golongan Darah
        add(new JLabel("Golongan Darah:"));
        golonganDarah = new JComboBox<>(new String[]{"A", "B", "O", "AB"});
        add(golonganDarah);

        // Alamat
        add(new JLabel("Alamat:"));
        alamat = new JTextField();
        add(alamat);

        // RT/RW
        add(new JLabel("RT/RW:"));
        rtRw = new JTextField();
        add(rtRw);

        // Kelurahan/Desa
        add(new JLabel("Kelurahan/Desa:"));
        kelurahan = new JTextField();
        add(kelurahan);

        // Kecamatan
        add(new JLabel("Kecamatan:"));
        kecamatan = new JTextField();
        add(kecamatan);

        // Agama
        add(new JLabel("Agama:"));
        agama = new JComboBox<>(new String[]{"Islam", "Kristen", "Hindu", "Budha", "Konghucu"});
        add(agama);

        // Status Perkawinan
        add(new JLabel("Status Perkawinan:"));
        statusPerkawinan = new JComboBox<>(new String[]{"Belum Menikah", "Menikah", "Janda/Duda"});
        add(statusPerkawinan);

        // Kewarganegaraan
        add(new JLabel("Kewarganegaraan:"));
        wni = new JRadioButton("WNI");
        wna = new JRadioButton("WNA");
        ButtonGroup kewarganegaraanGroup = new ButtonGroup();
        kewarganegaraanGroup.add(wni);
        kewarganegaraanGroup.add(wna);
        JPanel kewarganegaraanPanel = new JPanel();
        kewarganegaraanPanel.add(wni);
        kewarganegaraanPanel.add(wna);
        add(kewarganegaraanPanel);

        // Berlaku Hingga
        add(new JLabel("Berlaku Hingga:"));
        berlakuHingga = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(berlakuHingga, "yyyy-MM-dd");
        berlakuHingga.setEditor(dateEditor2);
        add(berlakuHingga);

        // Kota Pembuatan
        add(new JLabel("Kota Pembuatan:"));
        kotaPembuatan = new JTextField();
        add(kotaPembuatan);

        // Tanggal Pembuatan
        add(new JLabel("Tanggal Pembuatan:"));
        tanggalPembuatan = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor3 = new JSpinner.DateEditor(tanggalPembuatan, "yyyy-MM-dd");
        tanggalPembuatan.setEditor(dateEditor3);
        add(tanggalPembuatan);

        submitButton = new JButton("Submit");
        add(submitButton);
        backButton = new JButton("Back to Main Menu");
        add(backButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (NIK.getText().isEmpty() || nama.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field yang wajib!");
                    return;
                }
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO data_ktp (NIK, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, golongan_darah, " +
                            "alamat, rt_rw, kelurahan, kecamatan, agama, status_perkawinan, kewarganegaraan, berlaku_hingga, " +
                            "kota_pembuatan, tanggal_pembuatan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, NIK.getText());
                    statement.setString(2, nama.getText());
                    statement.setString(3, tempatLahir.getText());
                    statement.setString(4, ((JSpinner.DateEditor) tanggalLahir.getEditor()).getFormat().format(tanggalLahir.getValue()));
                    statement.setString(5, pria.isSelected() ? "Pria" : "Wanita");
                    statement.setString(6, (String) golonganDarah.getSelectedItem());
                    statement.setString(7, alamat.getText());
                    statement.setString(8, rtRw.getText());
                    statement.setString(9, kelurahan.getText());
                    statement.setString(10, kecamatan.getText());
                    statement.setString(11, (String) agama.getSelectedItem());
                    statement.setString(12, (String) statusPerkawinan.getSelectedItem());
                    statement.setString(13, wni.isSelected() ? "WNI" : "WNA");
                    statement.setString(14, ((JSpinner.DateEditor) berlakuHingga.getEditor()).getFormat().format(berlakuHingga.getValue()));
                    statement.setString(15, kotaPembuatan.getText());
                    statement.setString(16, ((JSpinner.DateEditor) tanggalPembuatan.getEditor()).getFormat().format(tanggalPembuatan.getValue()));

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data!");
                }
            }
        });

        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Record::new);
    }
}
