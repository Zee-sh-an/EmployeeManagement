package com.management.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {

    private final String message;

    private String code;


}
