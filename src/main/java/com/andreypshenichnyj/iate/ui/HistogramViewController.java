package com.andreypshenichnyj.iate.ui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistogramViewController {

    @FXML
    private Label xTitleLabel;
    @FXML
    private Label yTitleLabel;

    @FXML
    private BarChart<String, Number> xBarChart;
    @FXML
    private CategoryAxis xXAxis;

    @FXML
    private BarChart<String, Number> yBarChart;
    @FXML
    private CategoryAxis yXAxis;

    public void setHistogramData(String xField, List<Double> xValues, String yField, List<Double> yValues) {
        xBarChart.setVisible(false);
        xTitleLabel.setVisible(false);
        yBarChart.setVisible(false);
        yTitleLabel.setVisible(false);

        if (xField != null && !xValues.isEmpty()) {
            xTitleLabel.setText("Гистограмма по полю: " + xField);
            xBarChart.getData().add(createHistogramSeries(xField, xValues));
            xBarChart.setVisible(true);
            xTitleLabel.setVisible(true);
        }

        if (yField != null && !yValues.isEmpty()) {
            yTitleLabel.setText("Гистограмма по полю: " + yField);
            yBarChart.getData().add(createHistogramSeries(yField, yValues));
            yBarChart.setVisible(true);
            yTitleLabel.setVisible(true);
        }
    }

    private XYChart.Series<String, Number> createHistogramSeries(String label, List<Double> values) {
        int bins = 10;
        double min = Collections.min(values);
        double max = Collections.max(values);
        double binSize = (max - min) / bins;

        Map<String, Integer> histogram = new LinkedHashMap<>();
        for (int i = 0; i < bins; i++) {
            double lower = min + i * binSize;
            double upper = lower + binSize;
            String range = String.format("[%.1f - %.1f)", lower, upper);
            histogram.put(range, 0);
        }

        for (double value : values) {
            int index = Math.min((int) ((value - min) / binSize), bins - 1);
            String key = (String) histogram.keySet().toArray()[index];
            histogram.put(key, histogram.get(key) + 1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(label);
        for (Map.Entry<String, Integer> entry : histogram.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        return series;
    }
}
