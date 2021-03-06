package com.harnet.quest.controller;

import com.harnet.quest.model.board.Cell;
import com.harnet.quest.model.board.CellType;
import com.harnet.quest.model.board.GameMap;
import com.harnet.quest.model.actors.Actor;
import com.harnet.quest.model.actors.Player;
import com.harnet.quest.model.actors.RustMonster;
import com.harnet.quest.model.actors.Skeleton;
import com.harnet.quest.model.items.Bones;
import com.harnet.quest.model.items.Key;
import com.harnet.quest.model.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'e':
                            cell.setType(CellType.EXIT);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Skeleton skel = new Skeleton(cell);
                            Actor.enemyActors.add(skel); // add skeleton to EnemiesArray
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            RustMonster rustMonster = new RustMonster(cell);
                            Actor.enemyActors.add(rustMonster); // add rust monster to EnemiesArray
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case '/':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                            case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bones(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
