import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton btnPerekaman = new JButton("Perekaman");
        JButton btnPencarian = new JButton("Pencarian");
        JButton btnExit = new JButton("Exit");

        btnPerekaman.addActionListener(e -> {
            GUI perekaman = new GUI();
            perekaman.setVisible(true);
            dispose();
        });

        btnPencarian.addActionListener(e -> {
            Search search = new Search();
            search.setVisible(true);
            dispose();
        });

        btnExit.addActionListener(e -> System.exit(0));

        add(btnPerekaman);
        add(btnPencarian);
        add(btnExit);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
