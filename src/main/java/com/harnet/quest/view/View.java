package com.harnet.quest.view;

import com.harnet.quest.model.board.Tiles;
import com.harnet.quest.model.board.GameMap;
import com.harnet.quest.controller.MapLoader;
import com.harnet.quest.model.actors.Player;
import com.harnet.quest.model.items.Item;
import com.harnet.quest.model.items.ItemType;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class View {
    public static GameMap map = MapLoader.loadMap(); // game map
    GridPane ui = new GridPane();
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

    public static void setMap(GameMap map) {
        View.map = map;
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

    public GridPane getUi() {
        return ui;
    }

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

    public void inventoryView(){
        int keysQuantity = map.getPlayer().getInventory().getItemQUantity(ItemType.KEY.getItemName());
        int bonesQuantity = map.getPlayer().getInventory().getItemQUantity(ItemType.BONES.getItemName());

        if ( keysQuantity > 0) {
            keyLabel.setText(ItemType.KEY.getItemName() +"key: " + keysQuantity);
        } else {
            keyLabel.setText("");
        }

        if (bonesQuantity > 0) {
            bonesLabel.setText("bones: " + bonesQuantity);
        } else {
            bonesLabel.setText("");
        }

        if (!map.getPlayer().isHasASword()) {
            swordLabel.setText("");
        }
    }

    public void pickUpMessage(Player player, Item item){
        switch (item.getTileName()) {
            //todo don't use normal strings, use enums
            case "sword":
                player.setHasASword(true);
                getSwordLabel().setText(item.getTileName());
                break;
            case "bones":
                getBonesLabel().setText("You picked a " + item.getTileName());
                break;
            case "key":
                getKeyLabel().setText("You picked a " + item.getTileName());
                break;
        }
    }
}
