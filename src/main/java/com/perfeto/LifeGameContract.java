package com.perfeto;

import java.awt.*;

public class LifeGameContract {
    public static final int BOX_SIZE = 10;
    public static final int UNIVERSE_WIDTH = 100;
    public static final int UNIVERSE_HEIGHT = 100;
    public static final int FPS = 300;

    public static Color getColor (Status status){
        switch (status) {
            case NONE:
                return Color.BLACK;
            case BORN:
                return Color.GREEN;
            case LIFE:
                return Color.WHITE;
            case DIED:
                return Color.RED;
        }
        return Color.BLUE;
    }

}
