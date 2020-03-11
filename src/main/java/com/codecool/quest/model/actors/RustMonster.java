package com.codecool.quest.model.actors;

import com.codecool.quest.model.board.Cell;

public class RustMonster extends Actor {
    private String name = "rustMonster";

    public RustMonster(Cell cell) {
        super(cell);
        super.health = 20;
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
