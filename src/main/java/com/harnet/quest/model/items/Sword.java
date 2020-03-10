package com.harnet.quest.model.items;

import com.harnet.quest.model.board.Cell;

public class Sword extends Item {

    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "sword";
    }
}
