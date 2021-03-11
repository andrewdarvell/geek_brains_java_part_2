package lessons.one.creatures;

public class Cat implements Creature, Jumpable {

    private final String name;
    private int jumpPower;
    private boolean out = false;

    public Cat(String name) {
        this.name = name;
        this.jumpPower = 6;
    }

    @Override
    public boolean jump(int obstacleDimension) {
        jumpPower -= obstacleDimension;
        return jumpPower >= 0;
    }

    @Override
    public boolean isAlive() {
        return !out;
    }

    @Override
    public String getCreatureName() {
        return name;
    }

    @Override
    public void outCreature() {
        out = true;
    }

}
