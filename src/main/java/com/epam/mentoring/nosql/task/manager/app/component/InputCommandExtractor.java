package com.epam.mentoring.nosql.task.manager.app.component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class designed for extracting command and command params from the user input.
 * @author Roman_Kovalchuk
 * */
public class InputCommandExtractor {

    public String extractCommand(String input){
        return input.substring(0, input.indexOf(' '));
    }

    public Map<String, Object> extractParams(String input) {
        String[] params = input.substring(input.indexOf(' ')).trim().split("(?= --)");
        return Arrays.stream(params)
                .map(String::trim)
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(p -> p[0], p -> p.length == 2 ? p[1] : p[0]));
    }

}
