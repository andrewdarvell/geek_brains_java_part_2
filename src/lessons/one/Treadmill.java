package lessons.one;

public class Treadmill extends Obstacle{

    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public void overcome(Creature creature) {
        creature.run(length);
    }
}
