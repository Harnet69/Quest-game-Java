package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;


import static com.codecool.quest.logic.CellType.DOOR;

public class Player extends Actor {
    private boolean isHasASword = false;
    private boolean isHasAkey = false;
    private int damage = 4;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        // if player has a sword
        if(isHasASword) {
            setDamage(5);
            return "playerWithSword";
        }
        return "player";
    }

    public boolean isHasASword() {
        return isHasASword;
    }

    public void setHasASword(boolean hasASword) {
        isHasASword = hasASword;
    }

    public boolean isHasAkey() {
        return isHasAkey;
    }

    public void setHasAkey(boolean hasAkey) {
        isHasAkey = hasAkey;
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
        Actor enemy = nextCell.getActor();
        if(enemy != null){
            System.out.println("Player fight with a skeleton!!!");
            enemy.decreaseHealth(damage);
            this.decreaseHealth(2);
            System.out.println(enemy.getHealth());
        }
        else if(nextCell.getTileName().equals("door")) {
            System.out.println("It's a locked door");
            if(map.getPlayer().isHasAkey){
                System.out.println("But you have a key");
                map.getCell(nextCell.getX(), nextCell.getY()).setType(CellType.FLOOR);
                map.getPlayer().setHasAkey(false);
            }
//            getCell().getActor().
//            if(nextCell.getTileName()) {
//
//            }
//            cell.setActor(null);
//            nextCell.setActor(this);
//            cell = nextCell;
        }
    }
}
