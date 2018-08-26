package com.hyd.dmaker;

import com.hyd.dmaker.elements.Element;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiding.he
 */
public class DiagramPane extends Pane {

    public static final int DEFAULT_ZOOM_PERCENT = 100;

    public static final int MIN_ZOOM_PERCENT = 20;

    public static final int MAX_ZOOM_PERCENT = 400;

    private double initialWidth, initialHeight;

    private double initialGridSize;

    private IntegerProperty zoomPercent = new SimpleIntegerProperty(DEFAULT_ZOOM_PERCENT);

    private BackgroundGrid backgroundGrid;

    private BooleanProperty showGrid = new SimpleBooleanProperty(false);

    private List<Element> diagramElements = new ArrayList<>();

    public DiagramPane(double initialWidth, double initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        this.setPrefSize(initialWidth, initialHeight);

        initComponents();
        initNodeEvents();
        initMouseEvents();
    }

    private void initNodeEvents() {
        this.visibleProperty().addListener((_ob, _old, _new) -> {
            if (_new) {
                updateContent();
            }
        });
    }

    public void addElement(Element element) {
        this.diagramElements.add(element);
    }

    public int getZoomPercent() {
        return zoomPercent.get();
    }

    public IntegerProperty zoomPercentProperty() {
        return zoomPercent;
    }

    public void setZoomPercent(int zoomPercent) {
        this.zoomPercent.set(zoomPercent);
    }

    public boolean isShowGrid() {
        return showGrid.get();
    }

    public BooleanProperty showGridProperty() {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid.set(showGrid);
    }

    private void initComponents() {
        this.backgroundGrid = new BackgroundGrid();

        this.initialGridSize = this.backgroundGrid.getGridSize();
        this.getChildren().add(this.backgroundGrid);

        this.backgroundGrid.widthProperty().bind(this.prefWidthProperty());
        this.backgroundGrid.heightProperty().bind(this.prefHeightProperty());
        this.backgroundGrid.disableProperty().bind(this.showGrid.not());
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
        if (getZoomPercent() <= MIN_ZOOM_PERCENT) {
            return;
        }

        setZoomPercent(getZoomPercent() - 5);
        updateContent();
    }

    private void zoomIn() {
        if (getZoomPercent() >= MAX_ZOOM_PERCENT) {
            return;
        }

        setZoomPercent(getZoomPercent() + 5);
        updateContent();
    }

    ///////////////////////////////////////////////////////////////

    private void updateContent() {
        int zoomPercent = getZoomPercent();

        this.setPrefSize(
                this.initialWidth * zoomPercent / 100,
                this.initialHeight * zoomPercent / 100
        );

        updateBackgroundGrid();
        updateElements();
    }

    private void updateElements() {

    }

    private void updateBackgroundGrid() {
        int zoomPercent = getZoomPercent();

        this.backgroundGrid.setGridSize(
                this.initialGridSize * zoomPercent / 100);

        this.backgroundGrid.refresh();
    }

    ///////////////////////////////////////////////////////////////
}
