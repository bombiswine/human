package imit.flat;

import imit.human.Human;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Flat {
    private final int number;
    private final int area;
    private final List<? extends Human> owners;


    public Flat(
        final int number,
        final int area,
        final List<? extends Human> owners
    ) {

        this.number = Optional.of(number)
            .filter(n -> n > 0)
            .orElseThrow(() -> new IllegalArgumentException("The number of flat cannot be negative"));

        this.area = Optional.of(area)
            .filter(n -> n > 0)
            .orElseThrow(() -> new IllegalArgumentException("The area of flat cannot be negative"));

        this.owners = Optional.of(owners)
            .filter(Objects::nonNull)
            .map(list -> list.stream()
                .filter(Objects::nonNull)
                .sorted((h1, h2) -> CharSequence.compare(h1.getFullNameAsString(), h2.getFullNameAsString()))
                .collect(Collectors.toList())
            ).orElseThrow(() -> new IllegalArgumentException("The null-ref passed as list of flat owners"));

    }

    public int getNumber() {
        return number;
    }

    public int getArea() {
        return area;
    }

    public List<? extends Human> getOwners() {
        return owners;
    }
}
