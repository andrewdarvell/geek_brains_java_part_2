package lessons.one;

public abstract class Creature {

    private final String creatureName;
    private int runPower;
    private int jumpPower;

    public Creature(String creatureName, int runPower, int jumpPower) {
        this.creatureName = creatureName;
        this.runPower = runPower;
        this.jumpPower = jumpPower;
    }

    public void run(int obstacleDimension) {
        runPower -= obstacleDimension;
        if (runPower >= 0) {
            System.out.printf("%s %s ran %d meters%n", getTypeName(), creatureName, obstacleDimension);
        } else {
            System.out.printf("%s %s don't ran %d meters%n", getTypeName(), creatureName, obstacleDimension);
        }
    }

    public void jump(int obstacleDimension) {
        jumpPower -= obstacleDimension;
        if (jumpPower >= 0) {
            System.out.printf("%s %s jumped %d meters%n", getTypeName(), creatureName, obstacleDimension);
        } else {
            System.out.printf("%s %s don't jumped %d meters%n", getTypeName(), creatureName, obstacleDimension);
        }

    }

    public boolean isAlive() {
        return runPower >= 0 && jumpPower >= 0;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public abstract String getTypeName();
}
