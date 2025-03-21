package com.perfumeria.custom.profile.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private String errorType;
    private String code;
    private String description;
}
