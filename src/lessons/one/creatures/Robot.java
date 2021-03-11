package lessons.one.creatures;

public class Robot implements Creature, Runnable, Jumpable {

    private final String name;
    private int runPower;
    private int jumpPower;
    private boolean out = false;

    public Robot(String name) {
        this.name = name;
        this.runPower = 20;
        this.jumpPower = 20;
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
    public boolean run(int obstacleDimension) {
        runPower -= obstacleDimension;
        return runPower >= 0;
    }

    @Override
    public void outCreature() {
        out = true;
    }
}
