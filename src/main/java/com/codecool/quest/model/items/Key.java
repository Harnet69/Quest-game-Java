package com.codecool.quest.model.items;

import com.codecool.quest.model.board.Cell;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "key";
    }
}
