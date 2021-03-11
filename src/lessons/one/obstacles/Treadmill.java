package lessons.one.obstacles;

import lessons.one.creatures.Creature;
import lessons.one.creatures.Runnable;

public class Treadmill implements Obstacle {

    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(Creature creature) {
        System.out.println(creature.getCreatureName() + "trying overcome Treadmill with length "+ length);
        if (creature instanceof Runnable) {
            return ((Runnable) creature).run(length);
        }
        return false;
    }
}
