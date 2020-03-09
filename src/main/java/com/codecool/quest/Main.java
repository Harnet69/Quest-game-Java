package com.codecool.quest;

import com.codecool.quest.logic.gameController.GameController;
import com.codecool.quest.logic.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    View view = new View();
    GameController gameController = new GameController(view);


    public static void main(String[] args) {
        launch(args);
    }

    // right side's panel
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        view.ui(ui);
        gameController.enemyMovement();
        gameController.pushPickUpButton();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(view.getCanvas());
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        gameController.refresh();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, gameController::playerMovements);
        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }
}
