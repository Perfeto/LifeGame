package com.perfeto;

public enum Status {
    NONE,       // 1
    BORN,       // 2
    LIFE,       // 1
    DIED;       // 2

    public Status step1(int around) {
        if (LifeGameContract.POPULATION_STATE == PopulationState.DEATH) {
            return NONE;
        }

        switch (this) {
            case NONE:
                if (LifeGameContract.POPULATION_STATE == PopulationState.DEVELOPMENT) {
                    return (around >= 3) ? BORN : NONE;
                } else if (LifeGameContract.POPULATION_STATE == PopulationState.STABILITY) {
                    return (around == 3) ? BORN : NONE;
                } else if (LifeGameContract.POPULATION_STATE == PopulationState.SUNSET) {
                    return this;
                }

            case LIFE:
                return (around <= 1 || around >= 4) ? DIED : LIFE;

            default:
                return this;
        }
    }

    public Status step2() {
        switch (this) {

            case BORN:
                return LIFE;

            case DIED:
                return NONE;

            default:
                return this;
        }
    }

    public boolean isCell (){
        return this == LIFE || this == DIED; // TODO try add born
    }
}
