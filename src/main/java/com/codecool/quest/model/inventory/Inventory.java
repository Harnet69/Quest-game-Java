package com.codecool.quest.model.inventory;

import com.codecool.quest.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    //todo don't declare ArrayList -> declare List, then
    private List<Item> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public List<String> getInventoryItems() {
        List<String> inventoryItems = new ArrayList<>();
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
