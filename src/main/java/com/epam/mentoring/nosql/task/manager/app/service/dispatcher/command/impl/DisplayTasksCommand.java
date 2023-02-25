package com.epam.mentoring.nosql.task.manager.app.service.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import com.epam.mentoring.nosql.task.manager.app.service.dispatcher.command.ConsoleCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DisplayTasksCommand implements ConsoleCommand {

    private final TaskService taskService;

    @Override
    public void process(Map<String, Object> params) {
        List<Task> tasks = null;
        if (params.containsKey("--all")){
            tasks = taskService.findAll();
        } else if (params.containsKey("--overdue")) {
            tasks = taskService.findOverdue();
        }
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String jsonTasks = mapper.writeValueAsString(tasks);
            System.out.println(jsonTasks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
