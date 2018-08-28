package com.hyd.dmaker;

import com.hyd.dmaker.shape.geometry.RectangleShape;
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

    private DiagramPane diagramPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        diagramPane = new DiagramPane(1001, 1001);

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
        primaryStage.setOnShown(event -> this.onStageShown());
        primaryStage.show();
    }

    private void onStageShown() {
        diagramPane.addShape(new RectangleShape(100, 200, 100, 100));
        diagramPane.addShape(new RectangleShape(250, 200, 100, 100));
        diagramPane.addShape(new RectangleShape(400, 200, 100, 100));
        diagramPane.addShape(new RectangleShape(550, 200, 100, 100));
        diagramPane.addShape(new RectangleShape(700, 200, 100, 100));
    }
}