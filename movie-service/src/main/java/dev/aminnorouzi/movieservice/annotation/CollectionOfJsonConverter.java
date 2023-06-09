package dev.aminnorouzi.movieservice.annotation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollectionOfJsonConverter extends JsonDeserializer<List<String>> {

    @Value("${movie.client.api.genre-field.name}")
    private String field;

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);

        List<String> genres = new ArrayList<>();
        node.forEach(n -> genres.add(n.findValue(field).asText()));

        return genres;
    }
}
