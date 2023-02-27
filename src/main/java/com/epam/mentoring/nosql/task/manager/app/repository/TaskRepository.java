package com.epam.mentoring.nosql.task.manager.app.repository;

import com.epam.mentoring.nosql.task.manager.app.dto.RelatedSubtaskDTO;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByDeadlineLessThan(LocalDateTime date);

    List<Task> findAllByCategory(Category category);

    @Query("{'description': {$regex:  ?0}}")
    List<Task> findAllByRegexpDescription(String regexp);

    @Aggregation(pipeline = {
            "{$unwind: '$subtasks'}",
            "{$match: {'subtasks.name': ?0}}",
            "{$project: {'subtask': '$subtasks', 'category': 1}}",
    })
    List<RelatedSubtaskDTO> findTasksBySubtasksName(String name);

}
