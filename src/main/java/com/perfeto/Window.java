package com.perfeto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window implements Runnable {

    private JFrame jFrame;
    private Box[][] boxes;
    private Timer timer;

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
        jFrame.setTitle("Life game. Mode STABILITY");
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {

                    case 32:
                        LifeGameContract.DEBUG_MODE = !LifeGameContract.DEBUG_MODE;
                        return;

                    case 38:
                        if (LifeGameContract.FPS >= 50) {
                            System.out.println("down");
                            LifeGameContract.FPS -= 50;
                            timer.setDelay(LifeGameContract.FPS);
                        }
                        return;

                    case 40:
                        if (LifeGameContract.FPS <= 1000) {
                            System.out.println("up");
                            LifeGameContract.FPS += 50;
                            timer.setDelay(LifeGameContract.FPS);
                        }
                        return;

                    case 81:
                        LifeGameContract.POPULATION_STATE = PopulationState.DEATH;
                        jFrame.setTitle("Life game. Mode DEATH");
                        return;

                    case 87:
                        LifeGameContract.POPULATION_STATE = PopulationState.SUNSET;
                        jFrame.setTitle("Life game. Mode SUNSET");
                        return;

                    case 69:
                        LifeGameContract.POPULATION_STATE = PopulationState.STABILITY;
                        jFrame.setTitle("Life game. Mode STABILITY");
                        return;

                    case 82:
                        LifeGameContract.POPULATION_STATE = PopulationState.DEVELOPMENT;
                        jFrame.setTitle("Life game. Mode DEVELOPMENT");
                        return;

                    case 84:
                        LifeGameContract.POPULATION_STATE = PopulationState.FULL_LIFE;
                        jFrame.setTitle("Life game. Mode FULL_LIFE");
                        return;
                }

            }
        });
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


//
//        for (int x = 10; x < 15; x++){
//
//            boxes[x][10].cell.setStatus(Status.LIFE);
//            boxes[x][10].setColor();
//        }

    }

    private void initTimer() {
        TimerListener tl = new TimerListener();
        timer = new Timer(LifeGameContract.FPS, tl);
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
