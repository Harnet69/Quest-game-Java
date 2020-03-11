package com.codecool.quest.model.actors;

import com.codecool.quest.model.board.Cell;

public class Skeleton extends Actor {
    private String name = "Skeleton";
    private int damage = 2;
    protected int health = 8;

    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
