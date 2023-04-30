package imit.house;

import imit.human.Human;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        if (!person.equals(houseHead)) {
            houseHead = Optional.ofNullable(person)
                .filter(Objects::nonNull)
                .orElseThrow(() -> new IllegalArgumentException("Error: invalid houseHead"));
        }
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
}
