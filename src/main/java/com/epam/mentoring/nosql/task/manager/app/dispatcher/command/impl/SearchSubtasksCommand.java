package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.service.SubtaskService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SearchSubtasksCommand implements ConsoleCommand {

    private final SubtaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--name")){
            commandResultPrinter.printStringOut("Cannot search subtasks by name! Name wasn't provided.");
            return;
        }
        String name = params.get("--name").toString();
        commandResultPrinter.printJsonOut(taskService.findAllByName(name));
    }
}