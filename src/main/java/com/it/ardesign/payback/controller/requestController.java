package com.it.ardesign.payback.controller;

import com.it.ardesign.payback.dto.RequestDTO;
import com.it.ardesign.payback.service.RequestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class requestController {

    @NonNull
    private final RequestService requestService;

    @PostMapping("/addToQueue")
    public String add(@RequestBody @Valid RequestDTO requestDTO) {
        requestService.addToQueue(requestDTO);

        return new String("SUCCESSFULLY ADDED TO QUEUE");
    }

    @GetMapping("/start")
    public String calculate() {
        requestService.calc();

        return new String("SUCCESSFULLY PROCESSED.");
    }

    ;
}
