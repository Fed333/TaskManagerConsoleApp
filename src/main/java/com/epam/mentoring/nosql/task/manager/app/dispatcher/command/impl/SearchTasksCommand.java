package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SearchTasksCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--word")){
            commandResultPrinter.printStringOut("Cannot search tasks by word in the description! Word wasn't provided.");
            return;
        }
        String word = params.get("--word").toString();
        commandResultPrinter.printJsonOut(taskService.findAllByWordInDescription(word));
    }
}
