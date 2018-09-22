package com.it.ardesign.payback.controller;

import com.it.ardesign.payback.dictionary.RequestType;
import com.it.ardesign.payback.service.RequestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class requestController {

    @NonNull
    private final RequestService requestService;

    @PostMapping("/addObject")
    public String addObject(@RequestParam @NonNull RequestType requestType, @RequestParam @NonNull String request) {
        requestService.addToQueue(requestType, request);

        return new String("SUCCESS"); //TODO: maybe ENUM?
    }

    @GetMapping("/startProcess")
    public String calculate() {
        requestService.calc();

        return new String("SUCCESS");
    }

    ;
}
