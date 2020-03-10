package com.codecool.quest.logic.model.actors;

import com.codecool.quest.logic.Cell;

public class Skeleton extends Actor {
    private String name = "Skeleton";
    private int damage = 2;
    protected int health = 8;

    public Skeleton(Cell cell) {
        super(cell);
//        super.health = 8; // change stats here or in a field?
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

    public void beat()  {
        System. out.println("Skeleton beat a player!!!");
    }

    public void move(int dx, int dy) {
//        super.move(dx, dy);
//        Cell nextCell = super.getCell().getNeighbor(dx, dy);
//        if(nextCell != null){
            System. out.println("Skeleton beat a player!!!");
//        }
    }

}
