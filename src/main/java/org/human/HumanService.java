package org.human;

import java.util.ArrayList;

public class HumanService {
    HumanService() { }

    public static Human[] getAdultsFrom(final Human[] people) {
        if (people == null) {
            throw new IllegalArgumentException(
                "The null was passed into getAdultsFrom method"
            );
        }
        ArrayList<Human> adults = new ArrayList <>();
        for (var person : people) {
            if (person.isAdult()) {
                adults.add(person);
            }
        }

        return adults.toArray(new Human[0]);
    }
}
