package com.dux.software.exceptions;

public class EquipoNoEncontradoException  extends RuntimeException {
    public EquipoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}