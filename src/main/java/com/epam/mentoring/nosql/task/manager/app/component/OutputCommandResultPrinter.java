package com.epam.mentoring.nosql.task.manager.app.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutputCommandResultPrinter {

    private final ObjectMapper objectMapper;

    public void printlnJsonOut(Object object) {
        try {
            String jsonTasks = objectMapper.writeValueAsString(object);
            System.out.println(jsonTasks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void printlnStringOut(String out) {
        System.out.println(out);
    }

    public void printStringOut(String out) {
        System.out.print(out);
    }

}
