package com.hyd.dmaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * @author yiding.he
 */
public class DiagramPaneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DiagramPane diagramPane = new DiagramPane(1000, 1000);
        diagramPane.setStyle("-fx-background-color: #AADDFF");

        ScrollPane scrollPane = new ScrollPane(diagramPane);

        primaryStage.setScene(new Scene(scrollPane, 600, 400));
        primaryStage.show();
    }
}