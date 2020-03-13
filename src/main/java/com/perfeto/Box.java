package com.perfeto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel {

    Cell cell;

    public Box(int x, int y) {
        super();
        cell = new Cell();
        setBounds(x * LifeGameContract.BOX_SIZE, y * LifeGameContract.BOX_SIZE,
                LifeGameContract.BOX_SIZE, LifeGameContract.BOX_SIZE);
        setBackground(LifeGameContract.getColor(Status.NONE));
        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    cell.runLife();
                    return;
                }

                if (e.getButton() == 2) {
                    cell.runTurn();
                    return;
                }

                if (e.getButton() == 3) {
                    cell.runDeath();
                    return;
                }


            }
        });
    }

    public void setColor() {
        setBackground(LifeGameContract.getColor(cell.getStatus()));
    }

    public void step1() {
        cell.step1();
        setColor();
    }

    public void step2() {
        cell.step2();
        setColor();
    }
}
