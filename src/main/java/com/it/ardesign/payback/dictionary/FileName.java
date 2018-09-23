package com.it.ardesign.payback.dictionary;

public enum FileName {
    AR_DESIGN("ARdesign");

    private String name;

    FileName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return System.getenv("TEMP").concat("\\").concat(this.name).concat(".txt");
    }
}
