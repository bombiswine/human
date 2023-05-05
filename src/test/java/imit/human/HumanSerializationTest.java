package imit.human;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static imit.TestingData.personAlexandreMerson;
import static org.testng.Assert.assertEquals;

public class HumanSerializationTest {
    @Test
    public static void serializeToJson_returnsAdequateJson_thenCorrect_test()
        throws JsonProcessingException {

        final Human originalPerson     = personAlexandreMerson;
        final ObjectMapper humanMapper = new ObjectMapper();
        final String jsonHuman         = humanMapper.writerWithDefaultPrettyPrinter().writeValueAsString(originalPerson);
        final Human deserializedHuman  = humanMapper.readValue(jsonHuman, Human.class);

        assertEquals(originalPerson, deserializedHuman);
    }
}
