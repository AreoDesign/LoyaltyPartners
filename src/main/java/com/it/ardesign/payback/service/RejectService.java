package com.it.ardesign.payback.service;

import org.springframework.stereotype.Service;

@Service
public class RejectService extends ParentService {

    @Override
    public String process(String record) {
        String msg = String.format("REQUEST '%s' HAS BEEN REJECTED.", record);
        return new String(msg);
    }
}
