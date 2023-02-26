package com.epam.mentoring.nosql.task.manager.app.dto;

import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RelatedSubtasksDTO {

    private String taskId;

    private Category category;

    private List<Subtask> subtasks;

}
