package com.ejemplo.demo.domain.service.estado;

import com.ejemplo.demo.api.dto.EstadoResponse;
import org.springframework.stereotype.Service;

@Service
public class EstadoSingletonService {

    private int valorActual;

    public EstadoResponse actualizar(int valor) {
        this.valorActual = valor;
        return respuesta();
    }

    public EstadoResponse obtener() {
        return respuesta();
    }

    public EstadoResponse reiniciar() {
        this.valorActual = 0;
        return respuesta();
    }

    private EstadoResponse respuesta() {
        return new EstadoResponse("singleton", valorActual);
    }
}
