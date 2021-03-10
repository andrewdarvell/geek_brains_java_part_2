package lessons.one;

public class Robot extends Creature {
    public Robot(String name) {
        super(name ,12, 12);
    }

    @Override
    public String getTypeName() {
        return "Robot";
    }
}
