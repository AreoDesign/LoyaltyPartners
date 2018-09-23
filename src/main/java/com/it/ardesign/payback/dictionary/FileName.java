package com.it.ardesign.payback.dictionary;

import lombok.Getter;

public enum FileName {
    AR_DESIGN("ARdesign");

    @Getter
    private String name;

    FileName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return System.getenv("TEMP").concat("\\").concat(this.name).concat(".txt");
    }
}
