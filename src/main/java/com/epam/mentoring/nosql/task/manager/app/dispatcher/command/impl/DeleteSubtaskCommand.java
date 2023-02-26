package com.epam.mentoring.nosql.task.manager.app.dispatcher.command.impl;

import com.epam.mentoring.nosql.task.manager.app.component.OutputCommandResultPrinter;
import com.epam.mentoring.nosql.task.manager.app.dispatcher.command.ConsoleCommand;
import com.epam.mentoring.nosql.task.manager.app.entity.Subtask;
import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class DeleteSubtaskCommand implements ConsoleCommand {

    private final TaskService taskService;

    private final OutputCommandResultPrinter commandResultPrinter;

    @Override
    public void process(Map<String, Object> params) {
        if (!params.containsKey("--taskId")){
            commandResultPrinter.printStringOut("Cannot delete subtask! Missing task id.");
            return;
        }
        if (!params.containsKey("--subtask")){
            commandResultPrinter.printStringOut("Cannot delete subtask! Missing subtask number.");
            return;
        }
        String taskId = params.get("--taskId").toString();
        int subtaskIndex = Integer.parseInt(params.get("--subtask").toString()) - 1;
        Optional<Task> optionalTask = taskService.findById(taskId);
        optionalTask.ifPresentOrElse(task -> {
            Optional.ofNullable(task.getSubtasks()).ifPresentOrElse(
                    subtasks -> {
                        long start = System.nanoTime();
                        if (subtasks.size() < subtaskIndex){
                            commandResultPrinter.printStringOut("Cannot delete subtask! Task with id: " + taskId + " doesn't have subtask with number: " + subtaskIndex);
                            return;
                        }
                        Subtask subtask = subtasks.remove(subtaskIndex);
                        taskService.save(task);
                        long end = System.nanoTime();
                        Map<String, Object> result = Map.of(
                                "Updated document", task,
                                "Deleted subtask", subtask,
                                "Time", TimeUnit.NANOSECONDS.toMillis(end - start) + " ms."
                        );
                        commandResultPrinter.printJsonOut(result);
                    },
                    () -> commandResultPrinter.printStringOut("Cannot delete subtask! Task with id: " + taskId + " doesn't have subtask with number: " + subtaskIndex)
            );
        }, () -> {
            commandResultPrinter.printJsonOut("Cannot delete subtask! No task with id: " + taskId + " was found.");
        });
    }
}
