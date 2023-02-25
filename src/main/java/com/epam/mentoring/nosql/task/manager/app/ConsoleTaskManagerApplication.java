package com.epam.mentoring.nosql.task.manager.app;

import com.epam.mentoring.nosql.task.manager.app.service.TaskManagerRunnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class ConsoleTaskManagerApplication {

    public static void main(String... args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:services.xml");
        context.start();
        log.info("ConsoleTaskManagerApplication has been started!");

        TaskManagerRunnerService runner = context.getBean(TaskManagerRunnerService.class);
        runner.runTaskManager();
        log.info("ConsoleTaskManagerApplication has been exited!");

    }
}
