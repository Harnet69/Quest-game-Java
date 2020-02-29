package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventory;

    public Inventory() {
        this.inventory = new ArrayList<Item>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public void removeFromInventory(Item item){
        inventory.remove(item);
    }
}
