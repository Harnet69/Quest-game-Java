package com.codecool.quest.model.items;

import com.codecool.quest.model.board.Cell;

public class Sword extends Item {

    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "sword";
    }
}
