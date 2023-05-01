package imit.house;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class HouseSerializationService {
    public static void serializeHouse(
        final @NotNull House house,
        final @NotNull Path filename
    ) throws IOException {
        Optional.of(house)
            .orElseThrow(IllegalArgumentException::new);

        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename.toFile()))) {
            oos.writeObject(house);
        }
    }

    public static House deserializeHouse(
        final @NotNull Path filename
    ) throws IOException, ClassNotFoundException {
        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename.toFile())))) {
            return (House) ois.readObject();
        }
    }
}
