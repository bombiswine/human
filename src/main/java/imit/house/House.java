package imit.house;

import imit.human.Human;

import java.util.*;
import java.util.stream.Collectors;

public class House {
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
//                .anyMatch(flat -> flat.getOwners().contains(houseHead))
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
}
