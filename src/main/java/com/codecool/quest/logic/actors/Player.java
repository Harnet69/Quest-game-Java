package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    private boolean isHasASword = false;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public boolean isHasASword() {
        return isHasASword;
    }

    public void setHasASword(boolean hasASword) {
        isHasASword = hasASword;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        Cell nextCell = super.getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            System.out.println("Fight with a skeleton!!!");
        };
    }
}
