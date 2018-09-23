package com.it.ardesign.payback.service;

import com.it.ardesign.payback.dictionary.FileName;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FileServiceTest {
    @Autowired
    private final FileService fileService = null;

    @Test
    public void processTest() throws IOException {
        int cnt = 5; //counter for lines creation
        int tmp = 0;
        List<String> savesStorage = new LinkedList<>();
        String data;
        //saving data into file and test map for assertion test
        while (tmp < cnt) {
            data = String.format("zapis do pliku nr %d", ++tmp);
            fileService.process(data);
            savesStorage.add(data);
        }

        //read data from file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FileName.AR_DESIGN.getFullPath()));
        Iterator<String> iterator = savesStorage.iterator();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Assert.assertTrue(iterator.hasNext());
            Assert.assertEquals(iterator.next(), line);
        }

        cleanAfterTest();

    }

    private void cleanAfterTest() {
        File file = new File(FileName.AR_DESIGN.getFullPath());

        if (file.delete()) {
            log.info(file.getName() + " has been deleted!");
        } else {
            log.error("Delete operation failed.");
        }

    }
}