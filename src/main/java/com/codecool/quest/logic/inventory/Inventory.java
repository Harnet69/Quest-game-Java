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

    public ArrayList<String> getInventoryItems() {
        ArrayList<String> inventoryItems = new ArrayList<>();
            for(Item invItem : inventory){
                inventoryItems.add(invItem.getTileName());
            }
        return inventoryItems;
    }

    public int getItemQUantity(String itemName){
        int itemQuantity = 0;
        for(Item invItem : inventory){
            if(invItem.getTileName().equals(itemName)){
                itemQuantity++;
            }
        }
        return itemQuantity;
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
