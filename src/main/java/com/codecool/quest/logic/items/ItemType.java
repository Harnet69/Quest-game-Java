package com.codecool.quest.logic.items;

public enum ItemType {
    BONES("bones"),
        KEY("key"),
    SWORD("sword");

    private final String itemName;

    ItemType(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
