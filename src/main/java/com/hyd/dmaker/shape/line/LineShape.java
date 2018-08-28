package com.hyd.dmaker.shape.line;

import com.hyd.dmaker.shape.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public class LineShape extends Shape {

    private List<Point2D> points = new ArrayList<>();

    public LineShape(double startX, double startY, double endX, double endY) {
        this.points.add(new Point2D(startX, startY));
        this.points.add(new Point2D(endX, endY));
    }

    @Override
    protected void drawShape(Canvas canvas) {
    }
}
