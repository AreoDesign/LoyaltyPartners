package com.it.ardesign.payback.service;

import com.google.common.collect.ImmutableMap;
import com.it.ardesign.payback.dictionary.RequestType;
import com.it.ardesign.payback.dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@Service
@Slf4j
public class RequestService {

    private final Map<RequestType, Processable> dispatcher;

    private Queue<RequestDTO> requestQueue = new ArrayDeque<>();

    public RequestService(DBService dbService,
                          RejectService rejectService,
                          FileService fileService,
                          LogService logService) {
        this.dispatcher = ImmutableMap.of(
                RequestType.DB, dbService,
                RequestType.REJECT, rejectService,
                RequestType.FILE, fileService,
                RequestType.LOG, logService
        );
    }

    public String addToQueue(RequestDTO requestDTO) {

        requestQueue.add(requestDTO);
        log.info("RequestService put record: '{}' onto queue. Now, there is: '{}' element(s) in queue.",
                requestDTO.getRecord(), requestQueue.size());

        return new String("SUCCESS");
    }

    public String calc() {

        RequestDTO requestDTO = null;
        boolean logFlag = requestQueue.size() != 0 ? true : false;

        long lStartTime = System.nanoTime();

        while (requestQueue.size() > 0) {
            requestDTO = requestQueue.poll();
            try {
                dispatcher.get(requestDTO.getRequestType()).process(requestDTO.getRecord());
            } catch (IOException e) {
                log.error("Something went wrong while dispatching the request...");
                e.printStackTrace();
            }
        }

        long lFinishTime = System.nanoTime();

        long timeElapsed = (lFinishTime - lStartTime) / 1_000_000; //time in milliseconds

        if (logFlag) {
            log.info("All elements processed within = {} msec. There is no requestDTO left.", timeElapsed);
        } else {
            log.warn("There was no requestDTO to process.");
        }

        return new String("SUCCESS");
    }

}
