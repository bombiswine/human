package imit.serializators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MyLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(
        final JsonParser jsonParser,
        final DeserializationContext deserializationContext
    ) throws IOException {
        final String localDate = jsonParser.getValueAsString();
        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(localDate, Timestamp.class).toLocalDateTime().toLocalDate();
    }
}
