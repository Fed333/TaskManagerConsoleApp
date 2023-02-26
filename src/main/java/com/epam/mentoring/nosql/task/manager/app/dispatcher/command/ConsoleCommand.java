package com.epam.mentoring.nosql.task.manager.app.dispatcher.command;

import java.util.Map;

/**
 * Interface, designed for different console command.
 * @author Roman_Kovalchuk
 * */
public interface ConsoleCommand {

    void process(Map<String, Object> params);

}
