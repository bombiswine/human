package imit.house;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static imit.TestingData.*;
import static org.testng.Assert.assertEquals;

public class HouseSerializationTest {
    public static final Path SERIALIZED_OBJECT_FILE_JSON = Path.of("src/test/java/imit/serialized_house.json");
    @BeforeClass
    public static void setUp() throws IOException {
        Files.createFile(SERIALIZED_OBJECT_FILE_JSON);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        Files.deleteIfExists(SERIALIZED_OBJECT_FILE_JSON);
    }

    @Test
    public static void serializeHouse_writesHouseToTxtFile_thenCorrect_test()
        throws IOException, ClassNotFoundException {

        new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .writerWithDefaultPrettyPrinter()
            .writeValue(SERIALIZED_OBJECT_FILE_JSON.toFile(), SMALL_HOUSE);

        final House deserializedHouse;
        try (final ObjectInput ois = new ObjectInputStream(
                new BufferedInputStream(
                    new FileInputStream(SERIALIZED_OBJECT_FILE_JSON.toFile())))
        ) {
            deserializedHouse = (House) ois.readObject();
        }

        assertEquals(deserializedHouse, SMALL_HOUSE);
    }

    @Test
    public static void serializeHouseUsingJacksonDataBind_test() throws JsonProcessingException {
        final ObjectMapper houseMapper       = new ObjectMapper();
        final String       jsonHouse         = houseMapper.writerWithDefaultPrettyPrinter().writeValueAsString(SMALL_HOUSE);
        final House        deserializedHouse = houseMapper.readValue(jsonHouse, House.class);

        assertEquals(SMALL_HOUSE, deserializedHouse);
    }
}