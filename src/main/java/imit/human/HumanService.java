package imit.human;

import java.util.ArrayList;

public class HumanService {
    HumanService() { }

    public static Human[] getAdultsFrom(final Human[] people) {
        if (people == null) {
            throw new IllegalArgumentException(
                "The null was passed to the getAdultsFrom method"
            );
        }
        for (var person : people) {
            if (person == null) {
                throw new IllegalArgumentException(
                    "The Human[] array passed to " +
                    "the getAdultsFrom method contains null"
                );
            }
        }

        ArrayList<Human> adults = new ArrayList <>();
        for (var person : people) {
            if (person.isAdult()) {
                adults.add(person);
            }
        }

        return adults.toArray(new Human[0]);
    }

    public static int[] getAgesArrayOf(final Human[] people) {
        if (people == null) {
            throw new IllegalArgumentException(
                "The null was passed to the getAdultsFrom method"
            );
        }
        for (var person : people) {
            if (person == null) {
                throw new IllegalArgumentException(
                    "The Human[] array passed to " +
                    "the getAdultsFrom method contains null"
                );
            }
        }

        int[] ages = new int[ people.length ];
        for (int i = 0; i < ages.length; i++) {
            ages[i] = people[i].getAge();
        }

        return ages;
    }
}
