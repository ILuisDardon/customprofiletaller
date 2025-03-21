package com.perfumeria.custom.profile.utils;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private String evento;
    private String origen;
    private String destino;
    private String exeption;
    private Object response;
    private int level;
    private String time;
}
