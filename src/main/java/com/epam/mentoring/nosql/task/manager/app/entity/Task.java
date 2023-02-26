package com.epam.mentoring.nosql.task.manager.app.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {

    private String id;

    private String name;

    private String description;

    @JsonSerialize(using = ToStringSerializer.class)
    @Field(name = "date_of_creation")
    private LocalDateTime creationDate;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime deadline;

    private Category category;

    private List<Subtask> subtasks;

}
