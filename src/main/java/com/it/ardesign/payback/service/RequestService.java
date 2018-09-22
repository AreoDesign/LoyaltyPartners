package com.it.ardesign.payback.service;

import com.google.common.collect.ImmutableMap;
import com.it.ardesign.payback.dictionary.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@Service
@Slf4j
public class RequestService {

    private final Map<RequestType, ParentService> dispatcher;

    private Queue<Pair<RequestType, String>> requestQueue = new ArrayDeque<>();

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

//    public String dispatch(String request){
//
//        return new String("SUCCESS");
//    }

    public String addToQueue(RequestType requestType, String request) {

        requestQueue.add(Pair.of(requestType, request));
        log.info("RequestService put request: '{}' onto queue. Now, there is: '{}' element(s) in queue.", request, requestQueue.size());

        return new String("SUCCESS");
    }

    public String calc() {

        while (requestQueue.size() > 0) {
            requestQueue.poll();
        }

        return new String("SUCCESS");
    }

}
