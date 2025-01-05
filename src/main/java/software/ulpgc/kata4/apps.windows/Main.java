package software.ulpgc.kata4.apps.windows;

import software.ulpgc.kata4.apps.windows.io.DatasetDownloader;
import software.ulpgc.kata4.apps.windows.view.MainFrame;
import software.ulpgc.kata4.architecture.control.ToggleCommand;
import software.ulpgc.kata4.architecture.io.tsv.MockBarchartLoader;
import software.ulpgc.kata4.architecture.io.tsv.TsvFileTitleDeserializer;
import software.ulpgc.kata4.architecture.io.tsv.TsvFileTitleReader;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.util.List;

public class Main {
    private static List<Title> titles;
    private static MainFrame frame;

    public static void main(String[] args) throws Exception {
        frame = new MainFrame();
        frame.add("toggle", new ToggleCommand(frame.display(), new MockBarchartLoader(loadTitles())))
                .setVisible(true);
    }

    private static List<Title> loadTitles() {
        titles = new TsvFileTitleReader(new File(DatasetDownloader.checkFile()), new TsvFileTitleDeserializer()).read();
        return titles;
    }
}
