package com.it.ardesign.payback.service;

import org.springframework.stereotype.Service;

@Service
public abstract class ParentService {

    public abstract String process(String request);

}
