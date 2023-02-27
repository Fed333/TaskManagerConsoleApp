package com.epam.mentoring.nosql.task.manager.app.service;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.DispatcherCommand;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class TaskManagerRunnerService {

    private static final String EXIT_COMMAND = "exit";

    private final Scanner console = new Scanner(System.in);
    private final DispatcherCommand dispatcherCommand;
    private final OutputCommandResultPrinter commandResultPrinter;

    public void runTaskManager() {
        printlnStartManager();

        String command;
        while (true) {
            printCommandInput();
            command = readCommandInput();
            if (isExitCommand(command)) {
                break;
            }
            dispatcherCommand.dispatchCommand(command);
        }

        printlnExitManager();
    }

    private void printlnStartManager() {
        commandResultPrinter.printlnStringOut("Welcome to task-manager.");
    }

    private void printlnExitManager() {
        commandResultPrinter.printlnStringOut("Exit the task-manager.");
    }

    private static boolean isExitCommand(String command) {
        return Optional.ofNullable(command).orElse("").contains(EXIT_COMMAND);
    }

    private void printCommandInput() {
        commandResultPrinter.printStringOut("task-manager> ");
    }

    private String readCommandInput() {
        return console.nextLine();
    }

}
