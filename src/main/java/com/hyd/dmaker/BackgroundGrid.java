package com.hyd.dmaker;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BackgroundGrid extends Canvas {

    private static final double DEFAULT_GRID_SIZE = 10;

    private DoubleProperty gridSize = new SimpleDoubleProperty(DEFAULT_GRID_SIZE);

    public BackgroundGrid() {
        this.disabledProperty().addListener((_ob, _old, _new) -> refresh());
        drawGrid();
    }

    public double getGridSize() {
        return gridSize.get();
    }

    public DoubleProperty gridSizeProperty() {
        return gridSize;
    }

    public void setGridSize(double gridSize) {
        this.gridSize.set(gridSize);
    }

    public void refresh() {
        drawGrid();
    }

    private void drawGrid() {

        double width = getWidth();
        double height = getHeight();
        double gridSize = getGridSize();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        if (gridSize < 2 || this.isDisabled()) {
            return;
        }

        Color gridColor = Color.web("#AAAAAA");
        gc.setStroke(gridColor);
        gc.setLineDashes(2, gridSize - 4, 2, 0);  // 构建类似十字准星的网格
        gc.setLineWidth(1);

        double x = 0, y = 0;
        while (x < width) {
            gc.strokeLine(x, 0, x, height);
            x += gridSize;
        }

        while (y < height) {
            gc.strokeLine(0, y, width, y);
            y += gridSize;
        }
    }
}
