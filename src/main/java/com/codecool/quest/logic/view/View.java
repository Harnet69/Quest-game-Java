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
    //todo ask is it correct to initialize it in fields of in constructor
    Label healthLabel = new Label();
    Button pickUpBtn = new Button("Pick Up");
    Label swordLabel = new Label();
    Label bonesLabel = new Label();
    Label keyLabel = new Label();

    public GameMap getMap() {
        return map;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getContext() {
        return context;
    }

    public Button getPickUpBtn() {
        return pickUpBtn;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public Label getSwordLabel() {
        return swordLabel;
    }

    public Label getBonesLabel() {
        return bonesLabel;
    }

    public Label getKeyLabel() {
        return keyLabel;
    }

    public void ui(GridPane ui){
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

    public void inventoryView(){
        if (map.getPlayer().getInventory().getItemQUantity("key") > 0) {
            keyLabel.setText("key: " + map.getPlayer().getInventory().getItemQUantity("key"));
        } else {
            keyLabel.setText("");
        }

        if (map.getPlayer().getInventory().getItemQUantity("bones") > 0) {
            bonesLabel.setText("bones: " + map.getPlayer().getInventory().getItemQUantity("bones"));
        } else {
            bonesLabel.setText("");
        }

        if (!map.getPlayer().isHasASword()) {
            swordLabel.setText("");
        }
    }
}
