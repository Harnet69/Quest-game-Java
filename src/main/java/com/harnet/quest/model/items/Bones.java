package com.harnet.quest.model.items;

import com.harnet.quest.model.board.Cell;

public class Bones extends Item {

    public Bones(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return ItemType.BONES.getItemName();
    }
}
