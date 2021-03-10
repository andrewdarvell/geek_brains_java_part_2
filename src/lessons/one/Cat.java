package lessons.one;

public class Cat extends Creature {

    public Cat(String name) {
        super(name, 7, 10);
    }

    @Override
    public String getTypeName() {
        return "Cat";
    }
}
