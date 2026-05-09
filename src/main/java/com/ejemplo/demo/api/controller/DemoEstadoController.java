package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.EstadoResponse;
import com.ejemplo.demo.api.generated.DemoEstadoApi;
import com.ejemplo.demo.domain.service.estado.EstadoManual;
import com.ejemplo.demo.domain.service.estado.EstadoSingletonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private final EstadoSingletonService estadoSingletonService;

    public DemoEstadoController(EstadoSingletonService estadoSingletonService) {
        this.estadoSingletonService = estadoSingletonService;
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarSingleton(@PathVariable Integer valor) {
        return ResponseEntity.ok(estadoSingletonService.actualizar(valor));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerSingleton() {
        return ResponseEntity.ok(estadoSingletonService.obtener());
    }

    @Override
    public ResponseEntity<EstadoResponse> reiniciarSingleton() {
        return ResponseEntity.ok(estadoSingletonService.reiniciar());
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarManual(@PathVariable Integer valor) {
        EstadoManual estadoManual = new EstadoManual();
        return ResponseEntity.ok(estadoManual.actualizar(valor));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerManual() {
        EstadoManual estadoManual = new EstadoManual();
        return ResponseEntity.ok(estadoManual.obtener());
    }
}
