package imit.house;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import imit.human.Human;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class House implements Serializable {
    private Human houseHead;
    private final String address;
    private final String cadastralNumber;
    private final List<Flat> flats;

//    public House() {
//        this.address = "";
//        this.cadastralNumber = "";
//        this.flats = new ArrayList<>(0);
//        this.houseHead = new Human(
//            new FullName("Alexandre", "Igorevich", "Merson"),
//            LocalDate.of(2002, 6, 24),
//            "Male",
//            "Russian"
//        );
//    }

    @JsonCreator
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

    @JsonGetter("houseHead")
    public Human getHouseHead() {
        return houseHead;
    }
    @JsonGetter("address")
    public String getAddress() {
        return address;
    }

    @JsonGetter("cadastralNumber")
    public String getCadastralNumber() {
        return cadastralNumber;
    }

    @JsonGetter("flats")
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

    @JsonValue
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

    public static void serializeTo(
        final House house,
        final Path filename
    ) throws IOException {
        Objects.requireNonNull(filename, "Error: the null ref passed as Path filename into House.serializeTo");
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
        objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objMapper.writeValue(filename.toFile(), house);
    }

    public static House deserializeFrom(
        final Path jsonFileName
    ) throws IOException {
        Optional.of(jsonFileName)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
//        objectMapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        return objectMapper.readValue(jsonFileName.toFile(), imit.house.House.class);
    }
}
