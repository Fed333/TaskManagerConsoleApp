package com.epam.mentoring.nosql.task.manager.app.repository;

import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
