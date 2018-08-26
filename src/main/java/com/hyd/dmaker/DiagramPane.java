package com.hyd.dmaker;

import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

/**
 * @author yiding.he
 */
public class DiagramPane extends Pane {

    private int zoomPercent = 100;

    private double initialWidth, initialHeight;

    public DiagramPane(double initialWidth, double initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.setPrefSize(initialWidth, initialHeight);
        initMouseEvents();
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
        if (zoomPercent <= 20) {
            return;
        }

        zoomPercent -= 5;
        updateContent();
    }

    private void zoomIn() {
        if (zoomPercent >= 400) {
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
    }
}
