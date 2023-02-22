package com.epam.mentoring.nosql.task.manager.app.service;

import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

}
