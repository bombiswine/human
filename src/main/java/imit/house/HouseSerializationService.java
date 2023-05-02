package imit.house;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class HouseSerializationService {
    public static void serializeHouse(
        final House house,
        final Path filename
    ) throws IOException {
        Optional.of(house)
            .orElseThrow(IllegalArgumentException::new);

        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filename.toFile()))
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
