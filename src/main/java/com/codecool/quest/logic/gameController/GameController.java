package com.codecool.quest.logic.gameController;

import com.codecool.quest.Tiles;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Bones;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.view.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameController {
    private GraphicsContext context;
    private Canvas canvas;
    private GameMap map;
    private Label healthLabel;
    Label swordLabel;
    Label bonesLabel;
    Label keyLabel;
    Button pickUpBtn;

    public GameController(View view) {
        this.context = view.getContext();
        this.canvas = view.getCanvas();
        this.map = view.getMap();
        this.healthLabel = view.getHealthLabel();
        this.swordLabel = view.getSwordLabel();
        this.bonesLabel = view.getBonesLabel();
        this.keyLabel = view.getKeyLabel();
        this.pickUpBtn = view.getPickUpBtn();
    }

    public void playerMovements(KeyEvent keyEvent) {
        if (map.getPlayer().getHealth() <= 0) {
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
        refresh();
        keyEvent.consume();

    }

    public void enemyMovement() {
        Timeline oneSecondWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // enemy movement each one sec
                for (int i = 0; i < Actor.enemyActors.size(); i++) {
                    Actor.enemyActors.get(i).moveBehaviour();
                }
                refresh();
            }
        }));
        oneSecondWonder.setCycleCount(Timeline.INDEFINITE);
        oneSecondWonder.play();
    }

    public void refresh() {
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
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    // push a pick up button
    public void pushPickUpButton() {
        pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
//            GameMap map = view.getMap();
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
    }
}
