package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

public interface TitleDeserializer {
    Title deserialize(String line);
}
