package software.ulpgc.kata4.apps.windows.view;

import software.ulpgc.kata4.architecture.view.ImportDialog;

import javax.swing.*;

public class SwingImportDialog extends JPanel implements ImportDialog {
    private final JTextField textField;

    public SwingImportDialog() {
        add(new JLabel("DB: "));
        add(this.textField = new JTextField());
        textField.setColumns(10);
    }


    @Override
    public String get() {
        return textField.getText();
    }
}
