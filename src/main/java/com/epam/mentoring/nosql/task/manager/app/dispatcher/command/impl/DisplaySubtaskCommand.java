package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.dto.RelatedSubtasksDTO;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.service.SubtaskService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DisplaySubtaskCommand implements ConsoleCommand {

    private final SubtaskService subtaskService;

    private final OutputCommandResultPrinter commandResultPrinter;

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
        commandResultPrinter.printlnJsonOut(relatedSubtasks);
    }
}