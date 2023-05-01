package imit.human;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class HumanSerializationService {
    public static void serializeHuman(
        final @NotNull Human human,
        final @NotNull Path filename
    ) throws IOException {
        Optional.of(human)
            .orElseThrow(IllegalArgumentException::new);

        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename.toFile()))) {
            oos.writeObject(human);
        }
    }

    public static Human deserializeHuman(
        final @NotNull Path filename
    ) throws IOException, ClassNotFoundException {
        Optional.of(filename)
            .filter(Files::exists)
            .orElseThrow(FileNotFoundException::new);

        try (ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename.toFile())))) {
            return (Human) ois.readObject();
        }
    }
}
