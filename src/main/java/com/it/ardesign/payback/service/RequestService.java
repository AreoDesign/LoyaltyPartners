package com.it.ardesign.payback.service;

import com.google.common.collect.ImmutableMap;
import com.it.ardesign.payback.dictionary.ProcessType;
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

    private final Map<ProcessType, Processable> dispatcher;

    private Queue<RequestDTO> requestQueue = new ArrayDeque<>();

    public RequestService(DBService dbService,
                          RejectService rejectService,
                          FileService fileService,
                          LogService logService) {
        this.dispatcher = ImmutableMap.of(
                ProcessType.DB, dbService,
                ProcessType.REJECT, rejectService,
                ProcessType.FILE, fileService,
                ProcessType.LOG, logService
        );
    }

    public String addToQueue(RequestDTO requestDTO) {
        requestQueue.add(requestDTO);
        log.info("RequestService put message: '{}' onto queue. Now, there is: '{}' element(s) in queue.",
                requestDTO.getMessage(), requestQueue.size());

        return "SUCCESS";
    }

    public String calc() {
        RequestDTO requestDTO;
        boolean logFlag = requestQueue.size() != 0 ? true : false;

        long lStartTime = System.nanoTime();

        while (requestQueue.size() > 0) {
            requestDTO = requestQueue.poll();
            try {
                dispatcher.get(requestDTO.getProcessType()).process(requestDTO.getMessage());
            } catch (IOException e) {
                log.error("Something went wrong while dispatching the request... See stack trace.");
                e.printStackTrace();
            }
        }

        long lFinishTime = System.nanoTime();

        long timeElapsed = (lFinishTime - lStartTime) / 1_000_000; //time in milliseconds

        if (logFlag) {
            log.info("All elements processed within = {} msec. There is no more requestDTO left.", timeElapsed);
        } else {
            log.warn("There was no items to process.");
        }

        return "PROCESSING COMPLETE";
    }

}
