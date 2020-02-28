package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;
import java.util.Random;

import static com.codecool.quest.logic.CellType.FLOOR;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    public static ArrayList<Actor> enemyActors = new ArrayList<Actor>();

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public int getHealth() {
        return health;
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


    public void decreaseHealth(int pointsToRemove){
        health -= pointsToRemove;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        // Interaction with environment
        CellType nextCellType = nextCell.getType();
        // actor can move only if the next cell is a floor and not an another actor
        if(nextCellType == FLOOR && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
//        else if(nextCellType == DOOR) {
//            System.out.println("It's a locked door");
//
////            if(nextCell.getTileName()) {
////
////            }
////            cell.setActor(null);
////            nextCell.setActor(this);
////            cell = nextCell;
//        }
    }

    // TO moveBehaviour for enemy
    public void moveBehaviour() {
        Random rand = new Random();

        if(cell.getX() > 0 && cell.getX() < 24 && cell.getY() > 0 && cell.getY() < 19){
        Cell nextCell = cell.getNeighbor(rand.nextInt(2 + 1) - 1, rand.nextInt(2 + 1) - 1);
            // Interaction with environment
            CellType nextCellType = nextCell.getType();
            // actor can move only if the next cell is a floor and not an another actor
            if (nextCellType == FLOOR && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }else{
                moveBehaviour();
            }

        }
    }
}
