package com.hyd.dmaker.shape;

import com.hyd.dmaker.shape.anchor.Anchor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape extends Pane {

    private List<Anchor> anchorList = new ArrayList<>();

    private Canvas canvas = new Canvas();

    private IntegerProperty zoomPercent = new SimpleIntegerProperty(100);

    private Point2D baseLocation;

    private Point2D baseSize;

    public Shape() {
        this.getChildren().add(canvas);
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());
        this.zoomPercent.addListener((_ob, _old, _new) -> refresh());
    }

    protected void setBaseBound(double x, double y, double width, double height) {
        this.baseLocation = new Point2D(x * getZoomPercent() / 100, y * getZoomPercent() / 100);
        this.baseSize = new Point2D(width * getZoomPercent() / 100, height * getZoomPercent() / 100);
    }

    public void setZoomPercentProperty(IntegerProperty zoomPercent) {
        this.zoomPercent = zoomPercent;
    }

    public void setZoomPercent(int zoomPercent) {
        this.zoomPercent.set(zoomPercent);
    }

    public int getZoomPercent() {
        return zoomPercent.get();
    }

    public void refresh() {
        setLayoutX(p(baseLocation.getX()));
        setLayoutY(p(baseLocation.getY()));
        setWidth(p(baseSize.getX()));
        setHeight(p(baseSize.getY()));
        drawShape(this.canvas);
    }

    protected double p(double d) {
        return d * getZoomPercent() / 100;
    }

    protected abstract void drawShape(Canvas canvas);
}
