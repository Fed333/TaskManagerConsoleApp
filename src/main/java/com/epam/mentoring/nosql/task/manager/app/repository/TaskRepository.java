package com.epam.mentoring.nosql.task.manager.app.repository;

import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByDeadlineLessThan(LocalDateTime date);

    List<Task> findAllByCategory(Category category);

}
