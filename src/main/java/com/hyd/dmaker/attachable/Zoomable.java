package com.hyd.dmaker.attachable;

import com.hyd.fx.attachable.Attachable;

import javax.xml.soap.Node;
import java.util.Collection;

public class Zoomable extends Attachable<Node> {

    protected Zoomable(Collection<? extends Node> t) {
        super(t);
    }
}
