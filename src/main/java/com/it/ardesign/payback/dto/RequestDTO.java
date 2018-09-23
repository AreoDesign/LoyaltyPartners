package com.it.ardesign.payback.dto;

import com.it.ardesign.payback.dictionary.ProcessType;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class RequestDTO {
    //informs how we want to process the message
    @NotNull
    @NonNull
    private ProcessType processType;

    //carries the message to process
    @NotNull
    @NonNull
    private String message;

}
