package com.codecool.quest.logic.model.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.model.inventory.Inventory;

public class Player extends Actor {
    private String name = "Player";
    private Inventory inventory;
    private boolean isHasASword = false;
    private boolean isHasAkey = false;
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
        if (super.getCell().getNeighbor(dx, dy) == null) {
            System.out.println("There is the exit!");
        } else {
            Cell nextCell = super.getCell().getNeighbor(dx, dy);
            Actor enemy = nextCell.getActor();
            if (enemy != null) {
                System.out.println("Player fight with a " + enemy.getName() + "!!!");
                if(enemy.getName().equals("rustMonster")) {
                    System.out.println("Without sword");
                    map.getPlayer().getInventory().removeFromInventory("sword");
                    isHasASword = false;
                }
                enemy.decreaseHealth(damage);
                this.decreaseHealth(enemy.getDamage());
                System.out.println(enemy.getHealth());
            } else if (nextCell.getTileName().equals("door") || nextCell.getTileName().equals("exit")) {
//                System.out.println("It's a locked door");
                if (map.getPlayer().getInventory().getItemQUantity("key") > 0) {
                    map.getCell(nextCell.getX(), nextCell.getY()).setType(CellType.FLOOR);
                    map.getPlayer().getInventory().removeFromInventory("key");
                }
            }
        }
    }
}
