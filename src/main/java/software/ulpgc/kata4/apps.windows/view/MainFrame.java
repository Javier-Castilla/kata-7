package software.ulpgc.kata4.apps.windows.view;

import software.ulpgc.kata4.architecture.control.Command;
import software.ulpgc.kata4.architecture.view.BarchartDisplay;
import software.ulpgc.kata4.architecture.view.ImportDialog;
import software.ulpgc.kata4.architecture.view.LoadingDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final BarchartDisplay display;
    private final ImportDialog importDialog;
    private final LoadingDisplay loadingDisplay;
    private final Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        setTitle("Kata3");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, createToolbar(this.importDialog = new SwingImportDialog()));
        add(BorderLayout.CENTER, (Component) (this.display = createBarchartDisplay()));
        this.loadingDisplay = createLoadingDisplay();
        this.commands = new HashMap<>();
    }

    private SwingLoadingDisplay createLoadingDisplay() {
        return new SwingLoadingDisplay(
                this,
                "Cargando datos...",
                "Por favor, espera. Cargando datos...",
                Dialog.ModalityType.APPLICATION_MODAL
        );
    }

    private JFreeBarchartDisplay createBarchartDisplay() {
        return new JFreeBarchartDisplay();
    }

    private JPanel createToolbar(ImportDialog importDialog) {
        JPanel panel = new JPanel();
        panel.add(createButton("Toggle"));
        panel.add(createButton("Import"));
        panel.add((Component) importDialog);
        return panel;
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name.toLowerCase()).execute());
        return button;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public BarchartDisplay display() {
        return display;
    }

    public LoadingDisplay loadingDisplay() {
        return loadingDisplay;
    }

    public ImportDialog loaderDialog() {
        return importDialog;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        commands.get("toggle").execute();
    }
}
