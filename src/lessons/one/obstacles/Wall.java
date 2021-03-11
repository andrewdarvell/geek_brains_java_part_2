package lessons.one.obstacles;

import lessons.one.creatures.Creature;
import lessons.one.creatures.Jumpable;

public class Wall implements Obstacle {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }


    @Override
    public boolean overcome(Creature creature) {
        System.out.println(creature.getCreatureName() + " trying overcome Wall with length "+ height);
        if (creature instanceof Jumpable) {
            return ((Jumpable) creature).jump(height);
        }
        return false;
    }
}
