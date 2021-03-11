package lessons.one;

import lessons.one.creatures.Cat;
import lessons.one.creatures.Creature;

public class Team {

    private final Creature[] party;

    public Team() {
        party = new Creature[]{new Cat("Boris")};
    }

    public Team(Creature... creatures){
        this.party = creatures;
    }

    public Creature[] getParty() {
        return party;
    }

    public void showResults() {
        System.out.println();
        System.out.println("***Team result***");
        for (Creature creature : party) {
            if (creature.isAlive()) {
                System.out.printf("+ %s passed all obstacles%n", creature.getCreatureName());
            } else {
                System.out.printf("- %s dropped out of the race%n", creature.getCreatureName());
            }
        }
    }
}
