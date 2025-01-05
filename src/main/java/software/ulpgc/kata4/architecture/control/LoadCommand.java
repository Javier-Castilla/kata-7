package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.io.BarchartLoader;
import software.ulpgc.kata4.architecture.io.TitleReader;
import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.view.LoadingDisplay;

import java.util.List;

public class LoadCommand implements Command {
    private final LoadingDisplay loadingDisplay;
    private final TitleReader reader;
    private final List<Title> titles;

    public LoadCommand(LoadingDisplay loadingDisplay, TitleReader reader, List<Title> titles) {
        this.loadingDisplay = loadingDisplay;
        this.reader = reader;
        this.titles = titles;
    }

    @Override
    public void execute() {
        loadingDisplay.showDisplay();
        new Thread(() -> {
            titles.clear();
            titles.addAll(reader.read());
            loadingDisplay.disposeDisplay();
        }).start();
    }
}
