package com.it.ardesign.payback.service;

import com.it.ardesign.payback.dao.RequestDAO;
import com.it.ardesign.payback.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService implements Processable {
    @Autowired
    private final RequestDAO requestDAO = null;

    @Override
    public String process(String message) {
        Request request = Request.builder()
                .record(message)
                .build();

        requestDAO.save(request);

        return new String("RECORD SUCCESSFULLY STORED IN DB");
    }
}
