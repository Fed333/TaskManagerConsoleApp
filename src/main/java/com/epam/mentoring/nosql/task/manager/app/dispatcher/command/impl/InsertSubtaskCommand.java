package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class InsertSubtaskCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;


    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--taskId")){
            commandResultPrinter.printlnStringOut("Cannot insert subtask! Missing task id.");
            return;
        }
        String taskId = params.get("--taskId").toString();

        Optional<Task> optionalTask = taskService.findById(taskId);
        optionalTask.ifPresentOrElse(task -> {
            long start = System.nanoTime();
            Subtask subtask = Subtask.builder()
                    .name(params.get("--name").toString())
                    .description(params.get("--description").toString()).build();
            Optional.ofNullable(task.getSubtasks()).ifPresentOrElse(
                    subtasks -> subtasks.add(subtask),
                    () -> task.setSubtasks(new ArrayList<>(List.of(subtask)))
            );
            taskService.save(task);
            long end = System.nanoTime();
            Map<String, Object> result = Map.of(
                    "Updated document", task,
                    "Added subtask", subtask,
                    "Time", TimeUnit.NANOSECONDS.toMillis(end - start) + " ms."
            );
            commandResultPrinter.printlnJsonOut(result);
        }, () -> {
            commandResultPrinter.printlnJsonOut("Cannot insert subtask! No task with id: " + taskId + " was found.");
        });
    }
}
