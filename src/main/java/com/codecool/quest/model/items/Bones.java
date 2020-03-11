package com.codecool.quest.model.items;

import com.codecool.quest.model.board.Cell;

public class Bones extends Item {

    public Bones(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "bones";
    }
}
