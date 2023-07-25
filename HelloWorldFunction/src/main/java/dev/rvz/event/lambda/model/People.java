package dev.rvz.event.lambda.model;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonCreator;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonFormat;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class People {
    private final String id;
    private final String name;
    private final LocalDate dateBirthday;
    private final String cpf;

    @JsonCreator
    public People(
            @JsonProperty("id") final String id,
            @JsonProperty("name") final String name,

            @JsonProperty("dateBirthday")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @JsonDeserialize(using = LocalDateDeserializer.class)
            @JsonSerialize(using = LocalDateSerializer.class)
            final LocalDate dateBirthday,
            @JsonProperty("cpf") final String cpf) {
        this.id = id;
        this.name = name;
        this.dateBirthday = dateBirthday;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
