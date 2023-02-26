package com.epam.mentoring.nosql.task.manager.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Subtask {

    private String name;
    private String description;

}
