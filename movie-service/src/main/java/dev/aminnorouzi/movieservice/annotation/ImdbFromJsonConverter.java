package dev.aminnorouzi.movieservice.annotation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class ImdbFromJsonConverter extends JsonDeserializer<String> {

    @Value("${movie.client.api.external-ids-field.imdb-id}")
    private String field;

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);

        if (node.has(field)) {
            return node.findValue(field).asText();
        }


        String s = node.asText();
        System.out.println(s);
        return s;
    }
}
