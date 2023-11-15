package com.faculdade.scholl.jackson;

import com.faculdade.scholl.model.Curso;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CursoSerializer extends JsonSerializer<Curso> {

    @Override
    public void serialize(Curso curso, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        if (curso.getId() != null) {
            jsonGenerator.writeNumberField("id", curso.getId());
        }
        jsonGenerator.writeStringField("nome", curso.getNome());
        jsonGenerator.writeStringField("descricao", curso.getDescricao());
        jsonGenerator.writeEndObject();
    }
}
