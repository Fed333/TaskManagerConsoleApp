package com.epam.mentoring.nosql.task.manager.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {

    private String id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private Category category;

    private List<Subtask> subtasks;

}
