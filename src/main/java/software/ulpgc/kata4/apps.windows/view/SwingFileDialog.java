package software.ulpgc.kata4.apps.windows.view;

import software.ulpgc.kata4.architecture.view.FileDialog;

import javax.swing.*;
import java.io.File;

public class SwingFileDialog extends JPanel implements FileDialog {
    private final JTextField textField;

    public SwingFileDialog() {
        add(new JLabel("File: "));
        add(this.textField = new JTextField());
        textField.setColumns(10);
    }

    @Override
    public File get() {
        return new File(textField.getText());
    }
}
