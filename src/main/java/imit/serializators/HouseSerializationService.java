package imit.serializators;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import imit.house.House;

public class HouseSerializationService {
    public static void serializeHouse(
        final House house,
        final Path filename
    ) throws IOException {
        Objects.requireNonNull(house, "House is null!");

        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectOutput oos = new ObjectOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(filename.toFile())))
        ) {
            oos.writeObject(house);
        }
    }

    public static House deserializeHouse(
        final Path filename
    ) throws IOException, ClassNotFoundException {
        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectInput ois = new ObjectInputStream(
            new BufferedInputStream(
                new FileInputStream(filename.toFile())))
        ) {
            return (House) ois.readObject();
        }
    }
}
