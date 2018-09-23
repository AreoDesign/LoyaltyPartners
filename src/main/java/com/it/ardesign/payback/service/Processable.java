package com.it.ardesign.payback.service;

import java.io.IOException;

public interface Processable {

    String process(String record) throws IOException;

}
