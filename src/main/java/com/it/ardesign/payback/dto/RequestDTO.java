package com.it.ardesign.payback.dto;

import com.it.ardesign.payback.dictionary.RequestType;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class RequestDTO {
    //informs how we want to process the record
    @NotNull
    @NonNull
    private RequestType requestType;

    //carries the record to process
    @NotNull
    @NonNull
    private String record;

}
