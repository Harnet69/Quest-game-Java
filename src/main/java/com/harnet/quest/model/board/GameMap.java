package com.harnet.quest.model.board;

import com.harnet.quest.model.actors.Player;
import com.harnet.quest.model.actors.Skeleton;
import com.harnet.quest.model.items.Item;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Skeleton skeleton;
    private Item item;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
            return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
