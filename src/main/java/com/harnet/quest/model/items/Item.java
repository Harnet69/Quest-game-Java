package com.codecool.quest.logic.model.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private boolean isPickedUp;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
        this.isPickedUp = false;
    }


    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }
}
