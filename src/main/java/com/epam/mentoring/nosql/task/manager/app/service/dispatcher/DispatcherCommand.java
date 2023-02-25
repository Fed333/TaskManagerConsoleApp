package com.epam.mentoring.nosql.task.manager.app.service.dispatcher;

import com.epam.mentoring.nosql.task.manager.app.service.dispatcher.command.ConsoleCommand;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DispatcherCommand {

    private final Map<String, ConsoleCommand> commands;

    private final InputCommandExtractor inputExtractor;

    public void dispatchCommand(String command) {
        commands.get(inputExtractor.extractCommand(command)).process(inputExtractor.extractParams(command));
    }

}
