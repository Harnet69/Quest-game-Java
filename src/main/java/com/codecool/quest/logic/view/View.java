package com.codecool.quest.logic.view;

import com.codecool.quest.Tiles;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class View {
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

    public void ui(){
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
    }

//    pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent e) {
//            if (map.getPlayer().getCell().getItem() != null) {
//                Player player = map.getPlayer();
//                Item item = map.getPlayer().getCell().getItem();
//                player.getInventory().addToInventory(item); // put item to an inventory
//
//                switch (item.getTileName()) {
//                    //todo don't use normal strings, use enums
//                    case "sword":
//                        player.setHasASword(true);
//                        swordLabel.setText(item.getTileName());
//                        break;
//                    case "bones":
//                        bonesLabel.setText("You picked a " + item.getTileName());
//                        break;
//                    case "key":
//                        keyLabel.setText("You picked a " + item.getTileName());
//                        break;
//                }
//
//                map.getCell(player.getCell().getX(), player.getCell().getY()).setItem(null);
//                gameController.refresh();
//            }
//        }
//
//    });
}
