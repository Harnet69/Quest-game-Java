package com.harnet.quest.model.actors;

import com.harnet.quest.controller.GameController;
import com.harnet.quest.controller.MapLoader;
import com.harnet.quest.model.board.Cell;
import com.harnet.quest.model.board.CellType;
import com.harnet.quest.model.board.GameMap;
import com.harnet.quest.model.inventory.Inventory;
import com.harnet.quest.model.items.ItemType;
import com.harnet.quest.view.View;

public class Player extends Actor {
    private String name = "Player";
    private Inventory inventory;
    private boolean isHasASword = false;
    private int damage = 3;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
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
        if (nextCell.getTileName().equals("exit")) {
            System.out.println("There is the exit!");
            GameController.startNewGame(MapLoader.loadMap("/map.txt"));
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
