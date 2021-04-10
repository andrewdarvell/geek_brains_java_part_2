package partthree.fruitbox;

import java.util.LinkedList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<? extends Fruit>> {

    private final List<T> fruits = new LinkedList<>();

    public float getWeight() {
        if (fruits.size() == 0) {
            return 0;
        }
        return fruits.get(0).getWeight() * fruits.size();
    }

    public void clear() {
        fruits.clear();
    }

    public List<T> getFruits() {
        return fruits;
    }

    /**
     * Method cleaning input box!
     *
     * @param otherBox
     */
    public void addBulk(Box<T> otherBox) {
        fruits.addAll(otherBox.getFruits());
        otherBox.clear();
    }

    @Deprecated
    public boolean compare(Box<? extends Fruit> otherBox) {
        return otherBox != null && otherBox.getWeight() == this.getWeight();
    }

    /**
     * if otherBox > 0 :  otherBox larger
     * @param otherBox
     * @return
     * @throws NullPointerException
     */
    @Override
    public int compareTo(Box<? extends Fruit> otherBox) throws NullPointerException {
        return Float.compare(this.getWeight(), otherBox.getWeight());
    }


    public int getFruitsCount() {
        return fruits.size();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }
}
