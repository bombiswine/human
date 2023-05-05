package imit.house;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static imit.TestingData.FLAT;
import static org.testng.Assert.assertEquals;

public class FlatSerializationTest {
    @Test
    public static void flatSerializationToJsonUsingBin_test() throws JsonProcessingException {
        final Flat         originalFlat     = FLAT;
        final ObjectMapper flatMapper       = new ObjectMapper();
        final String       jsonFlat         = flatMapper.writerWithDefaultPrettyPrinter().writeValueAsString(originalFlat);
        final Flat         deserializedFlat = flatMapper.readValue(jsonFlat, Flat.class);

        assertEquals(originalFlat, deserializedFlat);
    }
}
