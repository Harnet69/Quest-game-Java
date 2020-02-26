package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Player;
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

public class Main extends Application {
    GameMap map = MapLoader.loadMap(); // game map
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpBtn = new Button("Pick Up");
    Label items = new Label();
    Label bones = new Label();
    Label key = new Label();


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
        ui.add(items, 0, 3);
        ui.add(bones, 0, 4);
        ui.add(key, 0, 5);

        // push a pick up button
        pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (map.getPlayer().getCell().getItem() != null) {
                    Player player = map.getPlayer();
                    if(map.getPlayer().getCell().getItem().getTileName().equals("sword")) {
                        player.setHasASword(true);
                        items.setText(map.getPlayer().getCell().getItem().getTileName());
                    }
                    else if(map.getPlayer().getCell().getItem().getTileName().equals("bones")) {
                        bones.setText(map.getPlayer().getCell().getItem().getTileName());
                    }
                    else if(map.getPlayer().getCell().getItem().getTileName().equals("key")) {
                        player.setHasAkey(true);
                        key.setText(map.getPlayer().getCell().getItem().getTileName());
                    }
                    else if(!player.isHasAkey()){
                        System.out.println("No key");
                        key.setText("no key");
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
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(map, 0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(map,0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(map,-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(map,1, 0);
                refresh();
                break;
        }
        // action after each player's movement
//        map.getSkeleton().move(map.getPlayer().getCell().getX(), map.getPlayer().getCell().getY());
        if(!map.getPlayer().isHasAkey()){
            key.setText("");
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
                } else {
                    cell.setActor(null);
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
//        pickUpBtn.setText("!!!!!" + map.getPlayer().getHealth());
    }
}
