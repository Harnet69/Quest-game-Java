package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Bones;
import com.codecool.quest.logic.items.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.jar.JarOutputStream;

public class Main extends Application {
    GameMap map = MapLoader.loadMap(); // game map
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpBtn = new Button("Pick Up");
    Label swordLabel = new Label();
    Label bonesLabel = new Label();
    Label keyLabel = new Label();


    public static void main(String[] args) {
        launch(args);
    }

    // right side's panel
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(pickUpBtn, 0, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(swordLabel, 0, 3);
        ui.add(bonesLabel, 0, 4);
        ui.add(keyLabel, 0, 5);

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                System.out.println("this is called every 1 seconds on UI thread");
                // enemy movement after each player's step
                for (int i = 0; i < Actor.enemyActors.size(); i++) {
                    Actor.enemyActors.get(i).moveBehaviour();
                }
                refresh();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();


        // push a pick up button
        pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (map.getPlayer().getCell().getItem() != null) {
                    Player player = map.getPlayer();
                    Item item = map.getPlayer().getCell().getItem();
                    player.getInventory().addToInventory(item); // put item to an inventory

                    switch (item.getTileName()) {
                        //todo don't use normal strings, use enums
                        case "sword":
                            player.setHasASword(true);
                            swordLabel.setText(item.getTileName());
                            break;
                        case "bones":
                            bonesLabel.setText("You picked a " + item.getTileName());
                            break;
                        case "key":
                            keyLabel.setText("You picked a " + item.getTileName());
                            break;
                    }

                    map.getCell(player.getCell().getX(), player.getCell().getY()).setItem(null);
                    refresh();
                }
            }

        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

//        scene.setOnKeyPressed(this::onKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);
//        pickUpBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::mo);
        primaryStage.setTitle("Codecool Quest");
//        primaryStage.requestFocus();
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if(map.getPlayer().getHealth() <= 0){
            System.out.println("Game Over");
           return;
        }

        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(map, 0, -1);
                break;
            case DOWN:
                map.getPlayer().move(map, 0, 1);
                break;
            case LEFT:
                map.getPlayer().move(map, -1, 0);
                break;
            case RIGHT:
                map.getPlayer().move(map, 1, 0);
                break;
        }
        refresh();



//        // enemy movement after each player's step
//        for (int i = 0; i < Actor.enemyActors.size(); i++) {
//            Actor.enemyActors.get(i).moveBehaviour();
//        }

//        show inventory
//        for(Item itemInInventory : map.getPlayer().getInventory().getInventory()) {
//            System.out.println(itemInInventory.getTileName());
//        }
//        System.out.println(map.getPlayer().getInventory().getInventoryItems());
        if(map.getPlayer().getInventory().getItemQUantity("key") > 0){
            keyLabel.setText("key: " + map.getPlayer().getInventory().getItemQUantity("key"));
        }else{
            keyLabel.setText("");
        }

        if(map.getPlayer().getInventory().getItemQUantity("bones") > 0){
            bonesLabel.setText("bones: " + map.getPlayer().getInventory().getItemQUantity("bones"));
        }else{
            bonesLabel.setText("");
        }

        if(!map.getPlayer().isHasASword()){
            swordLabel.setText("");
        }
        refresh();
        keyEvent.consume();
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                // Show actors on a map if they are alive
                if (cell.getActor() != null && cell.getActor().getHealth() > 0) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }
                // Show items on a map
                else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }
                // if actor is dead
                else if (cell.getActor() != null && cell.getActor().getHealth() <= 0) {
                    Actor.enemyActors.remove(cell.getActor()); // delete killed enemy from EnemyArray
                    cell.setActor(null);
                    cell.setItem(new Bones(cell));
                    Tiles.drawTile(context, cell, x, y);
                }
                else{
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
