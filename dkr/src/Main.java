import com.andrewdacenko.structures.DataGenerator;

import javax.swing.*;

public class Main {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            DataGenerator.initialize();

            MainForm main = new MainForm();
            main.setVisible(true);
        });
    }
}
