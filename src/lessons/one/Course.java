package lessons.one;

import lessons.one.creatures.Creature;
import lessons.one.obstacles.Obstacle;
import lessons.one.obstacles.Treadmill;
import lessons.one.obstacles.Wall;

public class Course {

    private final Obstacle[] obstacles;

    public Course() {
        obstacles = new Obstacle[]{
                new Wall(3),
                new Treadmill(5),
                new Wall(4),
                new Wall(6),
                new Treadmill(5),
                new Wall(1)
        };
    }

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void dolt(Team team) {
        for (Creature creature : team.getParty()) {
            System.out.println("=======");
            for (Obstacle obstacle : obstacles) {
                if (creature.isAlive()) {
                    if (obstacle.overcome(creature)) {
                        System.out.println(creature.getCreatureName() + " overcome obstacle");
                    } else {
                        System.out.println(creature.getCreatureName() + " not overcome obstacle");
                        creature.outCreature();
                        System.out.println(creature.getCreatureName() + " out of race");
                    }
                }
            }
        }
    }
}
