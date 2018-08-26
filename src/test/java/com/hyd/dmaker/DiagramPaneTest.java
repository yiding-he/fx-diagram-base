package com.hyd.dmaker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author yiding.he
 */
public class DiagramPaneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DiagramPane diagramPane = new DiagramPane(1000, 1000);
        ScrollPane scrollPane = new ScrollPane(diagramPane);
        scrollPane.setPannable(true);

        CheckBox chkDisplayGrid = new CheckBox("显示网格");
        chkDisplayGrid.setOnAction(event -> diagramPane.setShowGrid(chkDisplayGrid.isSelected()));

        Label percent = new Label();
        percent.textProperty().bind(diagramPane.zoomPercentProperty().asString().concat("%"));

        HBox hBox = new HBox(
                new Label("Ctrl + 鼠标滚轮缩放"),
                chkDisplayGrid,
                percent
        );

        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        BorderPane borderPane = new BorderPane(scrollPane);
        borderPane.setTop(hBox);

        primaryStage.setScene(new Scene(borderPane, 600, 400));
        primaryStage.show();
    }
}