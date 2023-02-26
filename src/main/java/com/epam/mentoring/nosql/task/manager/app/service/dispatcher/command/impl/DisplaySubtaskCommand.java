package com.epam.mentoring.nosql.task.manager.app.service.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.dto.RelatedSubtasksDTO;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.service.SubtaskService;
import com.epam.mentoring.nosql.task.manager.app.service.dispatcher.command.ConsoleCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class DisplaySubtaskCommand implements ConsoleCommand {

    private final SubtaskService subtaskService;

    private final ObjectMapper objectMapper;

    @Override
    public void process(Map<String, Object> params) {
        List<RelatedSubtasksDTO> relatedSubtasks = new ArrayList<>();
        if (params.containsKey("--all")) {
            if (params.containsKey("--category")){
                relatedSubtasks = subtaskService.findAllByCategory(Category.valueOf(params.get("--category").toString().toUpperCase()));
            } else {
                relatedSubtasks = subtaskService.findAll();
            }
        }
        try {
            String jsonTasks = objectMapper.writeValueAsString(relatedSubtasks);
            System.out.println(jsonTasks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
