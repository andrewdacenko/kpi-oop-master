import javax.swing.*;

public class Main {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            MainForm main = new MainForm();
            main.setVisible(true);
        });
    }
}
