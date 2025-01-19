package software.ulpgc.kata4.architecture.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Barchart {
    private final String title;
    private final String xAxis;
    private final String yAxis;
    private final Map<String, Integer> values;

    public Barchart(String title, String xAxis, String yAxis) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.values = new HashMap<>();
    }

    public String title() {
        return title;
    }

    public String xAxis() {
        return xAxis;
    }

    public String yAxis() {
        return yAxis;
    }

    public void put(String category, int value) {
        values.put(category, value);
    }

    public int get(String category) {
        return values.get(category);
    }

    public Set<String> keySet() {
        return values.keySet();
    }

    public int getOrDefault(String category, int value) {
        return values.getOrDefault(category, value);
    }
}
