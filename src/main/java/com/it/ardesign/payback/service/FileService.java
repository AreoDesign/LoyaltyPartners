package com.it.ardesign.payback.service;

import com.it.ardesign.payback.dictionary.FileName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class FileService implements Processable {

    @Override
    public String process(String record) throws IOException {
        FileWriter fileWriter = new FileWriter(FileName.AR_DESIGN.getFullPath(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            bufferedWriter.append(record).append(System.lineSeparator());
            log.info("The request has been saved to location: {}", FileName.AR_DESIGN.getFullPath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) bufferedWriter.close();
            if (fileWriter != null) fileWriter.close();
        }

        return new String("RECORD SUCCESSFULLY STORED IN FILE");
    }
}
