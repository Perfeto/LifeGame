package com.perfeto;

import java.awt.*;

public class LifeGameContract {
    public static boolean DEBUG_MODE = false;

    public static final int BOX_SIZE = 10;
    public static final int UNIVERSE_WIDTH = 100;
    public static final int UNIVERSE_HEIGHT = 100;
    public static int FPS = 50;
    public static PopulationState POPULATION_STATE = PopulationState.STABILITY;

    public static Color getColor(Status status) {
        switch (status) {
            case NONE:
                return Color.BLACK;
            case BORN:
                if (DEBUG_MODE) {
                    return Color.GREEN;
                } else {
                    return Color.GRAY;
                }
            case LIFE:
                return Color.WHITE;
            case DIED:
                if (DEBUG_MODE) {
                    return Color.RED;
                } else {
                    return Color.GRAY;
                }
        }
        return Color.BLUE;
    }

}
