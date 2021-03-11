package lessons.one.creatures;

public class Human implements Creature, Jumpable, Runnable {

    private final String name;
    private int runPower;
    private int jumpPower;
    private boolean out = false;

    public Human(String name) {
        this.name = name;
        this.runPower = 16;
        this.jumpPower = 16;
    }

    @Override
    public boolean jump(int obstacleDimension) {
        jumpPower -= obstacleDimension;
        return jumpPower >= 0;
    }

    @Override
    public boolean run(int obstacleDimension) {
        runPower -= obstacleDimension;
        return runPower >= 0;
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
