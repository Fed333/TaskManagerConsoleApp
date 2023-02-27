package com.epam.mentoring.nosql.task.manager.app.dto;

import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class RelatedSubtaskDTO {

    @Field("_id")
    private String taskId;

    private Category category;

    private Subtask subtask;

}