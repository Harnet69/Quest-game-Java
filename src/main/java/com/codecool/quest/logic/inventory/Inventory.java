package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Item> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public void removeFromInventory(String itemName){
        for(Item invItem : inventory){
            if(invItem.getTileName().equals(itemName)){
                inventory.remove(invItem);
                return;
            }
        }
    }
}
