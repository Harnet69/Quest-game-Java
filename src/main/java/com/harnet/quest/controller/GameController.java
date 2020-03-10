package com.harnet.quest.controller;

import com.harnet.quest.model.board.Tiles;
import com.harnet.quest.model.board.Cell;
import com.harnet.quest.model.board.GameMap;
import com.harnet.quest.model.actors.Actor;
import com.harnet.quest.model.actors.Player;
import com.harnet.quest.model.items.Bones;
import com.harnet.quest.model.items.Item;
import com.harnet.quest.view.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameController {
    View view;
    GameMap map;

    public GameController() {
        this.view = new View();
        this.map = view.getMap();
    }

    public View getView() {
        return view;
    }

    public void startGame(){
        getView().ui();
        enemyMovement();
        pushPickUpButton();
    }

    public GridPane getUi(){
        return view.getUi();
    }

    public void playerMovements(KeyEvent keyEvent) {
        GameMap map = view.getMap();
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
        view.inventoryView();
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
        GraphicsContext context = view.getContext();
        Canvas canvas = view.getCanvas();
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
        view.getHealthLabel().setText("" + map.getPlayer().getHealth());
    }

    // push a pick up button
    public void pushPickUpButton() {
        view.getPickUpBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (map.getPlayer().getCell().getItem() != null) {
                    Player player = map.getPlayer();
                    Item item = map.getPlayer().getCell().getItem();
                    player.getInventory().addToInventory(item); // put item to an inventory
                    view.pickUpMessage(player, item);
                    map.getCell(player.getCell().getX(), player.getCell().getY()).setItem(null);
                    refresh();
                }
            }

        });
    }
}
