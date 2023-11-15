package com.faculdade.scholl.jackson;

import com.faculdade.scholl.model.Curso;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListCursoDeserializer extends JsonDeserializer<List<Curso>> {


    @Override
    public List<Curso> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        List<Curso> cursos = new ArrayList<>();

        // Aqui você deve percorrer o nó do JSON e desserializar cada elemento como um Curso
        for (JsonNode cursoNode : node) {
            Curso curso = new Curso();

            JsonNode idNode = cursoNode.get("id");
            if (idNode != null && !idNode.isNull()) {
                curso.setId(idNode.asLong());
            }

            JsonNode nomeNode = cursoNode.get("nome");
            if (nomeNode != null && !nomeNode.isNull()) {
                curso.setNome(nomeNode.asText());
            }

            JsonNode descricaoNode = cursoNode.get("descricao");
            if (descricaoNode != null && !descricaoNode.isNull()) {
                curso.setDescricao(descricaoNode.asText());
            }

            cursos.add(curso);
        }

        return cursos;
    }
}
