package com.epam.mentoring.nosql.task.manager.app.dispatcher;

import com.epam.mentoring.nosql.task.manager.app.component.InputCommandExtractor;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
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
