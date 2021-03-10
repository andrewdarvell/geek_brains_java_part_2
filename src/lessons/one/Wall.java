package lessons.one;

public class Wall extends Obstacle {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }


    @Override
    public void overcome(Creature creature) {
        creature.jump(height);
    }
}
