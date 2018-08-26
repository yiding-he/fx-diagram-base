package com.hyd.dmaker;

import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

/**
 * @author yiding.he
 */
public class DiagramPane extends Pane {

    public static final int MIN_ZOOM_PERCENT = 20;
    public static final int MAX_ZOOM_PERCENT = 400;

    private int zoomPercent = 100;

    private double initialWidth, initialHeight;

    private double initialGridSize;

    private BackgroundGrid backgroundGrid;

    public DiagramPane(double initialWidth, double initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.setPrefSize(initialWidth, initialHeight);

        initComponents();
        initMouseEvents();
    }

    private void initComponents() {
        this.backgroundGrid = new BackgroundGrid();
        this.backgroundGrid.widthProperty().bind(this.widthProperty());
        this.backgroundGrid.heightProperty().bind(this.heightProperty());
        this.initialGridSize = this.backgroundGrid.getGridSize();

        this.getChildren().add(this.backgroundGrid);
        this.backgroundGrid.toBack();
    }

    private void initMouseEvents() {
        this.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.isControlDown()) {
                if (event.getDeltaY() > 0) {
                    zoomIn();
                } else if (event.getDeltaY() < 0) {
                    zoomOut();
                }
                event.consume();
            }
        });

        System.out.println("Mouse event filter set");
    }

    private void zoomOut() {
        if (zoomPercent <= MIN_ZOOM_PERCENT) {
            return;
        }

        zoomPercent -= 5;
        updateContent();
    }

    private void zoomIn() {
        if (zoomPercent >= MAX_ZOOM_PERCENT) {
            return;
        }

        zoomPercent += 5;
        updateContent();
    }

    private void updateContent() {
        this.setPrefSize(
                this.initialWidth * zoomPercent / 100,
                this.initialHeight * zoomPercent / 100
        );

        this.backgroundGrid.setGridSize(this.initialGridSize * zoomPercent / 100);
    }
}
