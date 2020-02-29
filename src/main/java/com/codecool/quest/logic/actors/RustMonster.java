package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class RustMonster extends Actor {
    private String name = "rustMonster";

    public RustMonster(Cell cell) {
        super(cell);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTileName() {
        return "rustMonster";
    }

    public void beat()  {
        System. out.println("Rust Monster beat a player!!!");
    }

    @Override
    public void moveBehaviour() {

    }
}
