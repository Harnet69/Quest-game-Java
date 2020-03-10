package com.codecool.quest.logic.model.items;

import com.codecool.quest.logic.Cell;

public class Bones extends Item {

    public Bones(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return ItemType.BONES.getItemName();
    }
}
