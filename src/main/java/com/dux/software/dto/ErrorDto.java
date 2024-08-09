package com.dux.software.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String mensaje;
    private int codigo;
}
