package imit.house;

import imit.human.Human;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static imit.TestingData.*;
import static imit.serializators.HouseSerializationService.deserializeHouse;
import static imit.serializators.HouseSerializationService.serializeHouse;
import static org.testng.Assert.assertEquals;

public class HouseSerializationServiceTest {
    public static final Path SERIALIZAD_OBJECT_FILE_TXT = Path.of("src/test/java/imit/serialized_house.txt");
    @BeforeClass
    public static void setUp() throws IOException {
        Files.createFile(SERIALIZAD_OBJECT_FILE_TXT);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        Files.deleteIfExists(SERIALIZAD_OBJECT_FILE_TXT);
    }

    @Test(dataProvider = "serializeHouse_writesHouseToTxtFile_thenCorrect_data")
    public static void serializeHouse_writesHouseToTxtFile_thenCorrect_test(
        final House originalHouse,
        final Path filename
    ) throws IOException, ClassNotFoundException {
        serializeHouse(originalHouse, filename);
        final House deserializedHouse = deserializeHouse(filename);
        assertEquals(deserializedHouse, originalHouse);
    }

    @DataProvider
    public static Object[][] serializeHouse_writesHouseToTxtFile_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> allFlats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        final House house = new House(houseHead, address, cadastralNumber, allFlats);

        return new Object[][] {
            { house, SERIALIZAD_OBJECT_FILE_TXT }
        };
    }
}