package com.hyd.dmaker.shape.geometry;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape extends GeometryShape {

    public RectangleShape(double x, double y, double width, double height) {
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setBaseBound(x, y, width, height);
    }

    @Override
    protected void drawShape(Canvas canvas) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);
        g.strokeRect(0, 0, getWidth(), getHeight());
    }
}
