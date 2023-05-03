package imit.serializators;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MyLocalDateSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(
        final LocalDate localDate,
        final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider
    ) throws IOException {
//        jsonGenerator.writeStartObject();
        Timestamp timestamp = Timestamp.valueOf(localDate.atTime(0,0,0));
        jsonGenerator.writeObject(timestamp);

//        jsonGenerator.writeString(localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth());
//        jsonGenerator.writeNumberField("year", localDate.getYear());
//        jsonGenerator.writeNumberField("month", localDate.getMonthValue());
//        jsonGenerator.writeNumberField("day", localDate.getDayOfMonth());
//        jsonGenerator.writeEndObject();
    }
}
