package software.ulpgc.kata7.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("Kata7");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(createLabel());
    }

    private static JLabel createLabel() {
        JLabel label = new JLabel("KATA7");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
