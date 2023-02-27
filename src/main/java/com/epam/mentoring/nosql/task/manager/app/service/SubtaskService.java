package com.epam.mentoring.nosql.task.manager.app.service;

import com.epam.mentoring.nosql.task.manager.app.dto.RelatedSubtaskDTO;
import com.epam.mentoring.nosql.task.manager.app.dto.RelatedSubtasksDTO;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SubtaskService {

    private final TaskRepository taskRepository;

    public List<RelatedSubtasksDTO> findAllByCategory(Category category) {
        return taskRepository.findAllByCategory(category).stream()
                .map(this::taskToRelatedSubtask)
                .collect(Collectors.toList());
    }
    public List<RelatedSubtasksDTO> findAll() {
        return taskRepository.findAll().stream()
                .map(this::taskToRelatedSubtask)
                .collect(Collectors.toList());
    }

    public List<RelatedSubtaskDTO> findAllByName(String name) {
        return taskRepository.findTasksBySubtasksName(name);
    }

    private RelatedSubtasksDTO taskToRelatedSubtask(Task task) {
        return RelatedSubtasksDTO.builder()
                .taskId(task.getId())
                .category(task.getCategory())
                .subtasks(task.getSubtasks()).build();
    }

}