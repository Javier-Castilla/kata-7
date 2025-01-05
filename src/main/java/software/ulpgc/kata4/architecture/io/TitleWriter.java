package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

import java.io.IOException;

public interface TitleWriter extends AutoCloseable {
    void write(Title title) throws IOException;
}
