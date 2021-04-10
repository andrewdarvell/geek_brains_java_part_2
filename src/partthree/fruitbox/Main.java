package partthree.fruitbox;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Apple> otherAppleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        otherAppleBox.addFruit(new Apple());
        otherAppleBox.addFruit(new Apple());
        otherAppleBox.addFruit(new Apple());

        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        otherAppleBox.addBulk(appleBox);



        System.out.println("Other Apple box fruit count: " + otherAppleBox.getFruitsCount());
        System.out.println("Compare boxes (orangeBox vs otherAppleBox): " + orangeBox.compareTo(otherAppleBox));


    }
}
