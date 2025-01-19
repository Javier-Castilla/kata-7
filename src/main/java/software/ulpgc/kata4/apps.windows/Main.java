package software.ulpgc.kata4.apps.windows;

import software.ulpgc.kata4.apps.windows.io.DatasetDownloader;
import software.ulpgc.kata4.apps.windows.view.MainFrame;
import software.ulpgc.kata4.architecture.control.ImportCommand;
import software.ulpgc.kata4.architecture.control.ToggleCommand;
import software.ulpgc.kata4.architecture.io.TitleReader;
import software.ulpgc.kata4.apps.windows.io.db.DatabaseTitleReader;
import software.ulpgc.kata4.architecture.io.tsv.MockBarchartLoader;
import software.ulpgc.kata4.architecture.io.tsv.TsvFileTitleDeserializer;
import software.ulpgc.kata4.architecture.io.tsv.TsvFileTitleReader;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.util.List;

public class Main {
    private static List<Title> titles;
    private static MainFrame frame;

    public static void main(String[] args) {
        frame = new MainFrame();
        frame.add("toggle", new ToggleCommand(frame.display(), new MockBarchartLoader(db())))
                .add("import", new ImportCommand(frame.loadingDisplay(), frame.loaderDialog(), titles))
                .setVisible(true);
    }

    private static List<Title> tsv() {
        titles = new TsvFileTitleReader(new File(DatasetDownloader.checkFile()), new TsvFileTitleDeserializer()).read();
        return titles;
    }

    private static List<Title> db() {
        try (TitleReader reader = DatabaseTitleReader.open(new File("example.db"))) {
            titles = reader.read();
            return titles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
