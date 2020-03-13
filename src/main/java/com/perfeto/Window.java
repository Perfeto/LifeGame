package com.perfeto;

import com.sun.org.apache.bcel.internal.generic.FADD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements Runnable {

    private JFrame jFrame;
    private Box[][] boxes;

    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    private void initFrame() {
        jFrame = new JFrame();
        jFrame.getContentPane().setLayout(null);
        jFrame.setSize(LifeGameContract.UNIVERSE_WIDTH * LifeGameContract.BOX_SIZE,
                LifeGameContract.UNIVERSE_HEIGHT * LifeGameContract.BOX_SIZE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setTitle("Life game");
    }

    private void initBoxes() {
        boxes = new Box[LifeGameContract.UNIVERSE_WIDTH][LifeGameContract.UNIVERSE_HEIGHT];
        for (int y = 0; y < LifeGameContract.UNIVERSE_HEIGHT; y++) {
            for (int x = 0; x < LifeGameContract.UNIVERSE_WIDTH; x++) {
                boxes[x][y] = new Box(x, y);
                jFrame.add(boxes[x][y]);
            }
        }

        // Для каждой ячейки обходим соседние ячейки кроме той, в кторой находимся
        for (int y = 0; y < LifeGameContract.UNIVERSE_HEIGHT; y++)
            for (int x = 0; x < LifeGameContract.UNIVERSE_WIDTH; x++)
                for (int sy = -1; sy <= 1; sy++)
                    for (int sx = -1; sx <= 1; sx++)
                        if (!(sy == 0 && sx == 0))
                            boxes[x][y].cell
                                    .addNear(boxes[(x + sx + LifeGameContract.UNIVERSE_WIDTH) % LifeGameContract.UNIVERSE_WIDTH]
                                            [(y + sy + LifeGameContract.UNIVERSE_HEIGHT) % LifeGameContract.UNIVERSE_HEIGHT].cell);



        for (int x = 10; x < 15; x++){

            boxes[x][10].cell.setStatus(Status.LIFE);
            boxes[x][10].setColor();
        }

    }

    private void initTimer() {
        TimerListener tl = new TimerListener();
        Timer timer = new Timer(LifeGameContract.FPS, tl);
        timer.start();
    }

    private class TimerListener implements ActionListener {
        boolean isItFirstStep = false;


        @Override
        public void actionPerformed(ActionEvent e) {
            isItFirstStep = !isItFirstStep;


            for (int y = 0; y < LifeGameContract.UNIVERSE_HEIGHT; y++)
                for (int x = 0; x < LifeGameContract.UNIVERSE_WIDTH; x++){
                    if (isItFirstStep){
                        boxes[x][y].step1();
                    } else {
                        boxes[x][y].step2();
                    }
                }

        }
    }
}
