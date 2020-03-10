package com.harnet.quest.model.items;

import com.harnet.quest.model.board.Cell;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "key";
    }
}
