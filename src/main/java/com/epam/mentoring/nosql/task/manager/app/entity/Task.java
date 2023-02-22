package com.epam.mentoring.nosql.task.manager.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {

    private String id;

    private String name;

    private String description;

    @Field(name = "date_of_creation")
    private LocalDateTime creationDate;

    private LocalDateTime deadline;

    private Category category;

    private List<Subtask> subtasks;

}
