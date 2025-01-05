package software.ulpgc.kata4.apps.windows.view;

import software.ulpgc.kata4.architecture.view.LoaderDialog;

import javax.swing.*;

public class SwingLoaderDialog extends JPanel implements LoaderDialog {
    private final JComboBox<String> comboBox;

    public SwingLoaderDialog() {
        add(new JLabel("Loader: "));
        add(this.comboBox = new JComboBox<>());
        comboBox.addItem("MockLoader");
    }


    @Override
    public String get() {
        return comboBox.getItemAt(comboBox.getSelectedIndex());
    }
}
