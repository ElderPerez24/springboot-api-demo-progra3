package com.ejemplo.demo.domain.service.estado;

import com.ejemplo.demo.api.dto.EstadoResponse;

public class EstadoManual {

    private int valorActual;

    public EstadoResponse actualizar(int valor) {
        this.valorActual = valor;
        return respuesta();
    }

    public EstadoResponse obtener() {
        return respuesta();
    }

    private EstadoResponse respuesta() {
        return new EstadoResponse("manual", valorActual);
    }
}
