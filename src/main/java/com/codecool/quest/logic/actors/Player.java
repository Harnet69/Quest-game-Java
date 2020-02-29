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
    private int damage = 3;

    private String name = "Player";

    public Player(Cell cell) {
        super(cell);
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
        if (super.getCell().getNeighbor(dx, dy) == null) {
            System.out.println("There is the exit!");
        } else {
            Cell nextCell = super.getCell().getNeighbor(dx, dy);
            Actor enemy = nextCell.getActor();
            if (enemy != null) {
                System.out.println("Player fight with a " + enemy.getName() + "!!!");
                if(enemy.getName().equals("rustMonster")) {
                    System.out.println("Without sword@");
                    isHasASword = false;
                }
                enemy.decreaseHealth(damage);
                this.decreaseHealth(enemy.getDamage());
                System.out.println(enemy.getHealth());
            } else if (nextCell.getTileName().equals("door") || nextCell.getTileName().equals("exit")) {
                System.out.println("It's a locked door");
                if (map.getPlayer().isHasAkey) {
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
}
