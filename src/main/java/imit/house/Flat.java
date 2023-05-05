package imit.house;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import imit.human.Human;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Flat implements Serializable {
    private final int number;
    private final int area;
    private final List<Human> owners;

    @JsonCreator
    public Flat(
        final @JsonProperty("number") int number,
        final @JsonProperty("area")   int area,
        final @JsonProperty("owners") List<Human> owners
    ) {

        this.number = Optional.of(number)
            .filter(n -> n > 0)
            .orElseThrow(() -> new IllegalArgumentException("The number of flat cannot be negative"));

        this.area = Optional.of(area)
            .filter(n -> n > 0)
            .orElseThrow(() -> new IllegalArgumentException("The area of flat cannot be negative"));

        this.owners = Optional.of(owners)
            .filter(list -> !list.isEmpty())
            .map(list -> list.stream()
                .filter(Objects::nonNull)
                .sorted((h1, h2) -> CharSequence.compare(h1.getFullNameAsString(), h2.getFullNameAsString()))
                .collect(Collectors.toList())
            ).orElseThrow(() -> new IllegalArgumentException(
                "Error: invalid flat owners list passed into Flat constructor")
            );

    }

    @JsonGetter("number")
    public int getNumber() {
        return number;
    }

    @JsonGetter("area")
    public int getArea() {
        return area;
    }

    @JsonGetter("owners")
    public List<Human> getOwners() {
        return owners;
    }

    public void changeOwners(
        final List<Human> newOwners
    ) {
        owners.clear();
        owners.addAll(Optional.of(newOwners)
            .map(list -> list.stream()
                .filter(Objects::nonNull)
                .sorted((h1, h2) -> CharSequence.compare(h1.getFullName().toString(), h2.getFullName().toString()))
                .collect(Collectors.toList())
            ).orElseThrow(() -> new IllegalArgumentException("Error: an attempt to replace owners list with null-ref"))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flat flat)) {
            return false;
        }
        return getNumber() == flat.getNumber()
            && getArea() == flat.getArea()
            && Objects.equals(getOwners(), flat.getOwners());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getArea(), getOwners());
    }

    @Override
    public String toString() {
        return new StringBuffer("Flat{")
            .append("number=").append(number)
            .append(", area=").append(area)
            .append(", owners=").append(owners)
            .append('}')
            .toString();
    }
}
