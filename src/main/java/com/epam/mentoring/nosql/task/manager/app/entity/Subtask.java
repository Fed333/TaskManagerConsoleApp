package com.epam.mentoring.nosql.task.manager.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subtask {

    private String name;
    private String description;

}
