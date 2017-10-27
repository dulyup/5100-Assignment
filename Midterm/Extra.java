package com.midterm;

import java.util.ArrayList;

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}

public class Extra {

    private static int[][] direction = {{1, 0}, {0, 1}};

    public ArrayList<Cell> findPath(int[][] maze) {
        ArrayList<Cell> result = new ArrayList<>();
        if (maze.length == 0 || maze[0].length == 0) {
            return result;
        }
        result.add(new Cell(0, 0));
        findPathHelper(maze, 0, 0, maze.length - 1, maze[0].length - 1, result);
        return result;
    }

    private boolean findPathHelper(int[][] maze, int startRow, int startCol, int endRow, int endCol, ArrayList<Cell> result) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        if (startRow > endRow || startCol > endCol) {
            return false;
        }
        for (int i = 0; i < direction.length; i++) {
            int nowRow = startRow + direction[i][0];
            int nowCol = startCol + direction[i][1];
            if (nowRow > endRow || nowCol > endCol || maze[nowRow][nowCol] == 0) {
                continue;
            }
            result.add(new Cell(nowRow, nowCol));
            boolean isFind = findPathHelper(maze, nowRow, nowCol, endRow, endCol, result);
            if (isFind) {
                return isFind;
            }
            result.remove(result.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{1, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
        ArrayList<Cell> result = new Extra().findPath(maze);
        System.out.print("[");
        int i = 0;
        for (Cell cell : result) {
            System.out.print("[" + cell.x + "," + cell.y + "]");
            i++;
            if (i != result.size()) {
                System.out.print(",");
            } else {
                System.out.print("]");
            }
        }
    }
    
}
