package com.it.ardesign.payback.service;

import com.it.ardesign.payback.dao.RequestDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBServiceTest {
    @Autowired
    private final RequestDAO requestDAO = null;
    @Autowired
    private final DBService dbService = null;

    private static final int RECORD_COUNT = 4;

    @Test
    public void dbServiceTest() {
        int cnt = 0;
        while (cnt < RECORD_COUNT) {
            dbService.process(String.format("Item no. '%d' is stored in H2 DB.", ++cnt));
        }

        Assert.assertEquals(RECORD_COUNT, requestDAO.findAll().size());
        Assert.assertNotNull(requestDAO.findById(1).orElseGet(null));
        Assert.assertEquals("Item no. '1' is stored in H2 DB.", requestDAO.findById(1).get().getRecord());

        //clean DB
        requestDAO.deleteAll();
    }

}