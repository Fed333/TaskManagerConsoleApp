package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class UpdateTaskCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--id")){
            commandResultPrinter.printlnStringOut("Cannot update task! Missing id.");
            return;
        }
        String id = params.get("--id").toString();
        Optional<Task> optionalTask = taskService.findById(id);
        optionalTask.ifPresentOrElse(task -> {
            long start = System.nanoTime();
            Optional.ofNullable(params.get("--name")).map(Object::toString).ifPresent(task::setName);
            Optional.ofNullable(params.get("--description")).map(Object::toString).ifPresent(task::setDescription);
            Optional.ofNullable(params.get("--creationDate")).map(Object::toString).map(LocalDateTime::parse).ifPresent(task::setCreationDate);
            Optional.ofNullable(params.get("--deadline")).map(Object::toString).map(LocalDateTime::parse).ifPresent(task::setDeadline);
            Optional.ofNullable(params.get("--category")).map(Object::toString).map(String::toUpperCase).map(Category::valueOf).ifPresent(task::setCategory);
            task = taskService.save(task);
            long end = System.nanoTime();
            Map<String, Object> result = Map.of(
                    "Updated document", task,
                    "Time", TimeUnit.NANOSECONDS.toMillis(end - start) + " ms."
            );
            commandResultPrinter.printlnJsonOut(result);
        }, () -> {
            commandResultPrinter.printlnStringOut("Cannot update task! No task with id: " + id + " was found.");
        });
    }
}
