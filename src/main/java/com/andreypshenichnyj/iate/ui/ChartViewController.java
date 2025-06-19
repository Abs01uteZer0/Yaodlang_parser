package com.andreypshenichnyj.iate.ui;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartViewController {

    @FXML
    private LineChart<Number, Number> lineChart;

    public void setChartData(String xLabel, String yLabel, List<XYChart.Data<Number, Number>> dataPoints) {
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

        xAxis.setForceZeroInRange(false);
        yAxis.setForceZeroInRange(false);

        lineChart.setTitle("График по полям: " + xLabel + " и " + yLabel);

        lineChart.getXAxis().setLabel(xLabel);
        lineChart.getYAxis().setLabel(yLabel);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(yLabel + " от " + xLabel);
        series.getData().addAll(dataPoints);

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }
}
