package lessons.one;

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
                obstacle.overcome(creature);
            }
        }
    }
}
