package com.it.ardesign.payback.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public abstract class ParentService {

    public abstract String process(String record) throws IOException;

}
