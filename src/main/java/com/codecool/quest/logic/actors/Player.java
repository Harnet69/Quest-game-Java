package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;

public class Player extends Actor {
    private boolean isHasASword = false;
    private int damage = 3;

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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Cell nextCell = super.getCell().getNeighbor(dx, dy);
        Actor enemy = nextCell.getActor();
        if(enemy != null){
            System.out.println("Player fight with a skeleton!!!");
            enemy.decreaseHealth(damage);
            this.decreaseHealth(2);
            System.out.println(enemy.getHealth());
        }
    }
}
