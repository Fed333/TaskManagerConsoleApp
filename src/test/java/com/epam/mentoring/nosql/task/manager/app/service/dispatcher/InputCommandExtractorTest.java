package com.epam.mentoring.nosql.task.manager.app.service.dispatcher;

import com.epam.mentoring.nosql.task.manager.app.dispatcher.InputCommandExtractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class InputCommandExtractorTest {

    private InputCommandExtractor extractor;

    @Before
    public void setUp() {
        extractor = new InputCommandExtractor();
    }

    @Test
    public void extractCommand_shouldExtractToFirstSpace() {
        String expected = "command";

        String actual = extractor.extractCommand("command  --param1 --param2 --param3");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void extractParams_shouldExtractFlags() {
        Map<String, Object> expected = Map.of(
                "--param1", "--param1",
                "--param2", "--param2",
                "--param3", "--param3"
        );

        Map<String, Object> actual = extractor.extractParams("command  --param1 --param2 --param3");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void extractParams_shouldExtractFlagParameters() {
        Map<String, Object> expected = Map.of(
                "--param1", "param1",
                "--param2", "param2",
                "--param3", "param3"
        );

        Map<String, Object> actual = extractor.extractParams("command  --param1=param1 --param2=param2 --param3=param3");

        Assert.assertEquals(expected, actual);
    }
}