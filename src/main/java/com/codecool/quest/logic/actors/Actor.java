package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codecool.quest.logic.CellType.FLOOR;

public abstract class Actor implements Drawable {
    private String name = "Actor";
    private Cell cell;
    protected int health = 10; //
    private int damage = 1;
    public static List<Actor> enemyActors = new ArrayList<Actor>();

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return damage;
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

    public String getName() {
        return name;
    }

    public void decreaseHealth(int pointsToRemove){
        health -= pointsToRemove;
    }

    //todo make abstract
    // make new class "enemy"
    // have enemy implement 'moveBehaviour' as 'move'
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
    }

    // moveBehaviour of enemies
    public void moveBehaviour() {
        Random rand = new Random();

        if(cell.getX() > 0 && cell.getX() < 24 && cell.getY() > 0 && cell.getY() < 19){
        Cell nextCell = cell.getNeighbor(rand.nextInt(3) - 1, rand.nextInt(3) - 1);
            // Interaction with environment
            CellType nextCellType = nextCell.getType();
            // actor can move only if the next cell is a floor and not an another actor
            if (nextCellType == FLOOR && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }else if(nextCellType == FLOOR && !nextCell.getActor().getName().equals("Skeleton")){
//                System.out.println(nextCell.getActor().getName());
//                System.out.println("Skeleton said Yummy!!!");
                moveBehaviour();
            }else{
                moveBehaviour();
            }
        }
    }
}
