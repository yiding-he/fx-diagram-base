package com.hyd.dmaker.shape.anchor;

import javafx.scene.layout.Pane;

public abstract class Anchor extends Pane {

    private AnchorDirection direction;

    public Anchor(AnchorDirection direction) {
        this.direction = direction;
    }

    public AnchorDirection getDirection() {
        return direction;
    }

    public void setDirection(AnchorDirection direction) {
        this.direction = direction;
    }
}
