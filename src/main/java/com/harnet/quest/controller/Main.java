package com.codecool.quest.logic.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
//    View view = new View();
    GameController gameController = new GameController();


    public static void main(String[] args) {
        launch(args);
    }

    // right side's panel
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        gameController.getView().ui(ui);
        gameController.enemyMovement();
        gameController.pushPickUpButton();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gameController.getView().getCanvas());
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        gameController.refresh();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, gameController::playerMovements);
        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }
}
