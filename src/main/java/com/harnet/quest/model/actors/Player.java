package com.harnet.quest.model.actors;

import com.harnet.quest.controller.GameController;
import com.harnet.quest.controller.MapLoader;
import com.harnet.quest.model.board.Cell;
import com.harnet.quest.model.board.CellType;
import com.harnet.quest.model.board.GameMap;
import com.harnet.quest.model.inventory.Inventory;
import com.harnet.quest.model.items.ItemType;
import com.harnet.quest.view.View;

public final class Player extends Actor {
    private static Player instance = null;
    private String name = "Player";
    private Inventory inventory;
    private boolean isHasASword = false;
    private int damage = 3;

    private Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
    }

    public static Player getInstance(Cell cell) {
        if (instance == null) {
            instance = new Player(cell);
        }
        return instance;
    }

    public String getTileName() {
        // if player has a sword
        if (isHasASword) {
            setDamage(5);
            return "playerWithSword";
        }else{
            setDamage(3);
            return "player";
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isHasASword() {
        return isHasASword;
    }

    public void setHasASword(boolean hasASword) {
        isHasASword = hasASword;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void move(GameMap map, int dx, int dy) {
        super.move(dx, dy);
        Cell nextCell = super.getCell().getNeighbor(dx, dy);
//        System.out.println(super.getCell().getX() + " : " + super.getCell().getY());
        System.out.println(map.getPlayer().getCell().getX() + " : " + map.getPlayer().getCell().getY());
        if (nextCell.getTileName().equals("exit")) {
            System.out.println("There is the exit!");
            GameController.startNewGame(MapLoader.loadMap("/map.txt")); // load new map
            GameController.getInstance().refresh();
        } else {
            Actor enemy = nextCell.getActor();
            if (enemy != null) {
                System.out.println("Player fight with a " + enemy.getName() + "!!!");
                if(enemy.getName().equals("rustMonster")) {
                    System.out.println("Without sword");
                    map.getPlayer().getInventory().removeFromInventory(ItemType.SWORD.getItemName());
                    isHasASword = false;
                }
                enemy.decreaseHealth(damage);
                this.decreaseHealth(enemy.getDamage());
                System.out.println(enemy.getHealth());
            } else if (nextCell.getTileName().equals("door") || nextCell.getTileName().equals("exit")) {
//                System.out.println("It's a locked door");
                if (map.getPlayer().getInventory().getItemQUantity(ItemType.KEY.getItemName()) > 0) {
                    map.getCell(nextCell.getX(), nextCell.getY()).setType(CellType.FLOOR);
                    map.getPlayer().getInventory().removeFromInventory(ItemType.KEY.getItemName());
                }
            }
        }
    }
}
