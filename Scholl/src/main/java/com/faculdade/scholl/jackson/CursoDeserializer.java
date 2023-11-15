package com.faculdade.scholl.jackson;

import com.faculdade.scholl.model.Curso;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CursoDeserializer extends JsonDeserializer<Curso> {

    @Override
    public Curso deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            Curso curso = new Curso();

            ObjectCodec codec = jsonParser.getCodec();
            JsonNode node = codec.readTree(jsonParser);

            JsonNode idNode = node.get("id");
            if (idNode != null && !idNode.isNull()) {
                curso.setId(idNode.asLong());
            }

            JsonNode nomeNode = node.get("nome");
            if (nomeNode != null && !nomeNode.isNull()) {
                curso.setNome(nomeNode.asText());
            }

            JsonNode descricaoNode = node.get("descricao");
            if (descricaoNode != null && !descricaoNode.isNull()) {
                curso.setDescricao(descricaoNode.asText());
            }

            return curso;
        } catch (JacksonException err) {
            throw new RuntimeException(err);
        }

    }

}
