package com.it.ardesign.payback.controller;

import com.it.ardesign.payback.dto.RequestDTO;
import com.it.ardesign.payback.service.RequestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RequestController {

    @NonNull
    private final RequestService requestService;

    @PostMapping("/queue")
    public ResponseEntity<String> add(@RequestBody @Valid RequestDTO requestDTO) {
        String response = requestService.addToQueue(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/start")
    public ResponseEntity<String> calculate() {
        String response = requestService.calc();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
