package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.io.TitleWriter;
import software.ulpgc.kata4.apps.windows.io.db.DatabaseTitleWriter;
import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.view.ImportDialog;
import software.ulpgc.kata4.architecture.view.LoadingDisplay;

import java.io.File;
import java.util.List;

public class ImportCommand implements Command {
    private final LoadingDisplay loadingDisplay;
    private final ImportDialog dialog;
    private final List<Title> titles;

    public ImportCommand(LoadingDisplay display, ImportDialog dialog, List<Title> titles) {
        loadingDisplay = display;
        this.dialog = dialog;
        this.titles = titles;
    }

    @Override
    public void execute() {
        new Thread(() -> {
            try (TitleWriter writer = DatabaseTitleWriter.open(new File(dialog.get()))) {
                loadingDisplay.showDisplay();
                for (Title title : titles) writer.write(title);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                loadingDisplay.disposeDisplay();
            }
        }).start();
    }
}
