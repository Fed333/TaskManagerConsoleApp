package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DisplayTasksCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        List<Task> tasks = null;
        if (params.containsKey("--all")){
            if (params.containsKey("--category")){
                tasks = taskService.findAllByCategory(Category.valueOf(params.get("--category").toString().toUpperCase()));
            } else {
                tasks = taskService.findAll();
            }
        } else if (params.containsKey("--overdue")) {
            tasks = taskService.findOverdue();
        }
       commandResultPrinter.printlnJsonOut(tasks);
    }
}
