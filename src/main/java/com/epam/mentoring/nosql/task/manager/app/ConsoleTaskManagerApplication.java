package com.epam.mentoring.nosql.task.manager.app;

import com.epam.mentoring.nosql.task.manager.app.entity.Task;
import com.epam.mentoring.nosql.task.manager.app.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
public class ConsoleTaskManagerApplication {

    public static void main(String... args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:services.xml");
        context.start();
        log.info("ConsoleTaskManagerApplication context has been started!");

        TaskService taskService = context.getBean(TaskService.class);
        List<Task> tasks = taskService.findAll();
        log.info("Tasks: {}", tasks);
    }
}
