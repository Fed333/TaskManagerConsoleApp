package com.epam.mentoring.nosql.task.manager.app.service;

import com.epam.mentoring.nosql.task.manager.app.service.dispatcher.DispatcherCommand;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class TaskManagerRunnerService {

    private final Scanner console = new Scanner(System.in);

    private final DispatcherCommand dispatcherCommand;

    public void runTaskManager() {
        printCommandInput();
        dispatcherCommand.dispatchCommand(readCommandInput());
    }

    private void printCommandInput() {
        System.out.print("task-manager> ");
    }

    private String readCommandInput() {
        return console.nextLine();
    }

}
