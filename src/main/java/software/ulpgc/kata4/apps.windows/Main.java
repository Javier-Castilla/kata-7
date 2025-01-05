package software.ulpgc.kata4.apps.windows;

import software.ulpgc.kata4.architecture.control.CommandFactory;
import software.ulpgc.kata4.architecture.io.*;
import software.ulpgc.kata4.apps.windows.view.MainFrame;
import software.ulpgc.kata4.architecture.control.LoadCommand;
import software.ulpgc.kata4.architecture.control.ToggleCommand;
import software.ulpgc.kata4.apps.windows.io.DatasetDownloader;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Title> titles = new ArrayList<>();
    private static Map<String, BarchartLoader> loaders = new HashMap<>();
    private static Map<String, TitleReader> readers = new HashMap<>();
    private static MainFrame frame;

    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        frame = new MainFrame(factory);
        initializeLoaders();
        initializeReaders();
        factory.register("toggle", toggleBuilder())
                .register("load", loadBuilder());
        frame.setVisible(true);
    }

    private static void initializeReaders() {
        readers.put("MockLoader", new TsvFileTitleReader(new File(DatasetDownloader.checkFile()), new TsvFileTitleDeserializer()));
    }

    private static void initializeLoaders() {
        loaders.put("MockLoader", new MockBarchartLoader(titles));
    }

    private static CommandFactory.Builder toggleBuilder() {
        return () -> {
            return new ToggleCommand(frame.display(), loaders.get(frame.loaderDialog().get()));
        };
    }

    private static CommandFactory.Builder loadBuilder() {
        return () -> {
            return new LoadCommand(frame.loadingDisplay(), readers.get(frame.loaderDialog().get()), titles);
        };
    }

    private static TitleReader createReader() {
        return new TsvFileTitleReader(new File(DatasetDownloader.checkFile()), new TsvFileTitleDeserializer());
    }
}
