package lessons.one;

import lessons.one.creatures.Cat;
import lessons.one.creatures.Human;
import lessons.one.creatures.Robot;

public class Main {

    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team(new Cat("Boris"), new Human("Valera"), new Cat("Kompot"), new Robot("Verter"));
        course.dolt(team);
        team.showResults();
    }
}
