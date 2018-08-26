package com.hyd.dmaker;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BackgroundGrid extends Canvas {

    private static final double DEFAULT_GRID_SIZE = 10;

    private DoubleProperty gridSize = new SimpleDoubleProperty(DEFAULT_GRID_SIZE);

    public BackgroundGrid() {
        this.widthProperty().addListener(this::sizeChanged);
        this.heightProperty().addListener(this::sizeChanged);
        this.gridSize.addListener(this::sizeChanged);
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

    private void sizeChanged(
            ObservableValue<? extends Number> ob, Number old, Number aNew) {
        refresh();
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

        if (gridSize < 2) {
            return;
        }

        Color gridColor = Color.web("#AAAAAA");
        gc.setStroke(gridColor);
        gc.setLineDashes(1, gridSize - 4, 3, 0);  // 构建类似十字准星的网格
        gc.setLineWidth(0.75);

        double x = -1, y = -1;
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
