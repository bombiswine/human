package imit.serializators;

import imit.house.Flat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FlatSerializationService {
    public static void serializeFlat(
        final Flat flat,
        final Path filename
    ) throws IOException {
        Optional.of(flat);
        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filename.toFile()))
        ) {
            oos.writeObject(flat);
        }
    }

    public static Flat deserializeFlat(
        final Path filename
    ) throws IOException, ClassNotFoundException {
        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (ObjectInput ois = new ObjectInputStream(
            new BufferedInputStream(
                new FileInputStream(filename.toFile())))
        ) {
            return (Flat) ois.readObject();
        }
    }
}
