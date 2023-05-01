package imit.house;

import imit.human.Human;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class House implements Serializable {
    private Human houseHead;
    private final String address;
    private final String cadastralNumber;
    private final List<Flat> flats;

    public House(
        final Human houseHead,
        final String address,
        final String cadastralNumber,
        final List<Flat> flats
    ) {
        this.houseHead = Optional.of(houseHead).get();

        this.address = Optional.of(address)
            .filter(s -> !s.isEmpty() && !s.isBlank())
            .orElseThrow(() -> new IllegalArgumentException("Error: invalid address"));

        this.cadastralNumber = Optional.of(cadastralNumber)
            .filter(s -> !s.isEmpty() && !s.isBlank())
            .orElseThrow(() -> new IllegalArgumentException("Error: invalid cadastral number"));

        this.flats = Optional.of(flats)
            .filter(list -> !list.isEmpty())
            .map(list -> list.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Flat::getNumber))
                .collect(Collectors.toList())
            ).orElseThrow(() -> new IllegalArgumentException("Error: invalid flats list"));
    }

    public void setHouseHead(final Human person) {
        if (!getResidents().contains(person)) {
            throw new IllegalArgumentException("Error: person isn't a resident of the house");
        }
        houseHead = Optional.of(person).orElseThrow(
            () -> new NullPointerException("Error: an attempt to replace current head of the house with null")
        );
    }

    public Human getHouseHead() {
        return houseHead;
    }

    public String getAddress() {
        return address;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public List<Human> getResidents() {
        Set<Human> residents = new HashSet<>();
        for (Flat flat : getFlats()) {
            residents.addAll(flat.getOwners());
        }

        return residents.stream().toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof House house)) {
            return false;
        }
        return Objects.equals(getHouseHead(), house.getHouseHead())
            && Objects.equals(getAddress(), house.getAddress())
            && Objects.equals(getCadastralNumber(), house.getCadastralNumber())
            && Objects.equals(getFlats(), house.getFlats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHouseHead(), getAddress(), getCadastralNumber(), getFlats());
    }

    @Override
    public String toString() {
        return new StringBuffer("House { ")
            .append("houseHead = ").append(houseHead)
            .append(", address = '").append(address).append('\'')
            .append(", cadastralNumber = '").append(cadastralNumber).append('\'')
            .append(", flats = ").append(flats)
            .append(" } ")
            .toString();
    }
}
