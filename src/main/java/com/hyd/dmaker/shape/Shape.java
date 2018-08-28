package com.hyd.dmaker.shape;

import com.hyd.dmaker.shape.anchor.Anchor;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape extends Pane {

    private List<Anchor> anchorList = new ArrayList<>();

    private Canvas canvas = new Canvas();

    private IntegerProperty zoomPercent;

    public Shape() {
        this.getChildren().add(canvas);
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());
    }

    public void setZoomPercentProperty(IntegerProperty zoomPercent) {
        this.zoomPercent = zoomPercent;
    }

    public void refresh() {
        drawShape(this.canvas);
    }

    protected abstract void drawShape(Canvas canvas);
}
