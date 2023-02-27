package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.entity.Category;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class InsertTaskCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        long start = System.nanoTime();
        Task task = Task.builder()
                .id((String) params.get("--id"))
                .name(params.get("--name").toString())
                .description(params.get("--description").toString())
                .creationDate(LocalDateTime.now())
                .deadline(LocalDateTime.parse(params.get("--deadline").toString()))
                .category(Category.valueOf(params.get("--category").toString().toUpperCase()))
                .subtasks(new ArrayList<>())
                .build();
        if (nonNull(task.getId()) && taskService.findById(task.getId()).isPresent()){
            commandResultPrinter.printlnStringOut("Cannot insert! Task with id: " + task.getId() + " already exists.");
            return;
        }
        task = taskService.save(task);
        long end = System.nanoTime();

        Map<String, Object> result = Map.of(
                "Inserted document", task,
                "Time", TimeUnit.NANOSECONDS.toMillis(end - start) + " ms."
        );
        commandResultPrinter.printlnJsonOut(result);
    }
}
