package lessons.one;

public class Human extends Creature {

    public Human(String name) {
        super(name, 15, 15);
    }

    @Override
    public String getTypeName() {
        return "Human";
    }
}
