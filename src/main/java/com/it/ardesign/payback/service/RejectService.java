package com.it.ardesign.payback.service;

import org.springframework.stereotype.Service;

@Service
public class RejectService implements Processable {

    @Override
    public String process(String record) {
        return String.format("REQUEST '%s' HAS BEEN REJECTED.", record);
    }
}
