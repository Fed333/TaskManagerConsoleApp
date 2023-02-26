package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class DeleteTaskCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--id")){
            commandResultPrinter.printStringOut("Cannot delete task! Missing id.");
            return;
        }
        String id = params.get("--id").toString();
        Optional<Task> optionalTask = taskService.findById(id);
        optionalTask.ifPresentOrElse(task -> {
            long start = System.nanoTime();
            taskService.delete(task);
            long end = System.nanoTime();
            Map<String, Object> result = Map.of(
                    "Deleted document", task,
                    "Time", TimeUnit.NANOSECONDS.toMillis(end - start) + " ms."
            );
            commandResultPrinter.printJsonOut(result);
        }, () -> {
            commandResultPrinter.printStringOut("Cannot delete task! No task with id: " + id + " was found.");
        });
    }
}
