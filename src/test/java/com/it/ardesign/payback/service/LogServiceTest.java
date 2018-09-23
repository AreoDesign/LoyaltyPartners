package com.it.ardesign.payback.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LogServiceTest {
    @Autowired
    private final LogService logService = null;

    @Test
    public void logServiceTest() {
        String msg = "this is the test for LOG service.";

        // get Logback Logger
        Logger logServiceLogger = (Logger) LoggerFactory.getLogger(LogService.class);

        // create and start a ListAppender
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        // addToQueue the appender to the logger
        logServiceLogger.addAppender(listAppender);

        // call method under test
        logService.process(msg);

        // JUnit assertions
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(LogService.formatMessage(msg), logsList.get(0).getFormattedMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());

    }

}