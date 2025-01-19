package software.ulpgc.kata4.apps.windows.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.architecture.model.Barchart;

public class JFreeBarchartAdapter {
    public static JFreeChart adapt(Barchart barchart) {
        return ChartFactory.createBarChart(
                barchart.title(),
                barchart.xAxis(),
                barchart.yAxis(),
                createDatasetFrom(barchart)
        );
    }

    private static DefaultCategoryDataset createDatasetFrom(Barchart barchart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        barchart.keySet().forEach(c -> dataset.addValue(barchart.get(c), "", c));
        return dataset;
    }
}
