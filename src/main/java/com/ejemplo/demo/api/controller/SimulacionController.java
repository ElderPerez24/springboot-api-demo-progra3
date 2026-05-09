package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SimulacionPrestamoRequest;
import com.ejemplo.demo.api.dto.SimulacionPrestamoResponse;
import com.ejemplo.demo.api.generated.SimulacionesApi;
import com.ejemplo.demo.domain.service.SimulacionPrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulacionController implements SimulacionesApi {

    private final SimulacionPrestamoService simulacionPrestamoService;

    public SimulacionController(SimulacionPrestamoService simulacionPrestamoService) {
        this.simulacionPrestamoService = simulacionPrestamoService;
    }

    @Override
    public ResponseEntity<SimulacionPrestamoResponse> simularPrestamo(
            @Valid @RequestBody SimulacionPrestamoRequest simulacionPrestamoRequest
    ) {
        return ResponseEntity.ok(
                simulacionPrestamoService.simular(
                        simulacionPrestamoRequest.monto(),
                        simulacionPrestamoRequest.tasaAnual(),
                        simulacionPrestamoRequest.meses()
                )
        );
    }
}
