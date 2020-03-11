package com.codecool.quest.model.items;

import com.codecool.quest.model.board.Cell;
import com.codecool.quest.model.board.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }
}
