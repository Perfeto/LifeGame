package com.perfeto;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private List<Cell> nearCells;
    private Status status;

    public Cell() {
        status = Status.NONE;
        nearCells = new ArrayList<Cell>();
    }

    void addNear(Cell cell) {
        nearCells.add(cell);
    }

    void step1() {
        int around = countNearCells();
        status = status.step1(around);
    }

    void step2() {
        status = status.step2();
    }

    private int countNearCells() {
        int count = 0;
        for (Cell cell :
                nearCells) {
            if (cell.status.isCell()) {
                count++;
            }
        }
        return count;
    }


//#######################################################################################

    public List<Cell> getNearCells() {
        return nearCells;
    }

    public void setNearCells(List<Cell> nearCells) {
        this.nearCells = nearCells;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void turn() {
        for (Cell cell : nearCells) {
            cell.setStatus(cell.status.isCell() ? Status.NONE : Status.LIFE);
        }


    }
}
