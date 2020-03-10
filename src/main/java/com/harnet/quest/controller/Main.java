package com.harnet.quest.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    // right side's panel
    @Override
    public void start(Stage primaryStage) throws Exception {

        GameController gameController = GameController.getInstance();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gameController.getView().getCanvas());
        borderPane.setRight(gameController.getUi());

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        gameController.refresh();


//        gameController.playerMove(scene);
        gameController.setScene(scene);
        gameController.startGame();
//        scene.addEventFilter(KeyEvent.KEY_PRESSED, gameController::playerMovements);
        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }
}
