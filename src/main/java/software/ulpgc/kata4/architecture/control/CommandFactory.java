package software.ulpgc.kata4.architecture.control;


import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> builders;

    public CommandFactory() {
        builders = new HashMap<>();
    }

    public CommandFactory register(String name, Builder builder) {
        builders.put(name, builder);
        return this;
    }

    public Builder get(String name) {
        return builders.get(name);
    }

    public interface Builder {
        Command build();
    }
}
