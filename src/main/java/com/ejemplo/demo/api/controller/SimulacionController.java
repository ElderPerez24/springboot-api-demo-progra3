package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SimulacionPrestamoRequest;
import com.ejemplo.demo.api.dto.SimulacionPrestamoResponse;
import com.ejemplo.demo.domain.service.SimulacionPrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulaciones")
public class SimulacionController {

    private final SimulacionPrestamoService simulacionPrestamoService;

    public SimulacionController(SimulacionPrestamoService simulacionPrestamoService) {
        this.simulacionPrestamoService = simulacionPrestamoService;
    }

    @PostMapping("/prestamo")
    public ResponseEntity<SimulacionPrestamoResponse> simularPrestamo(
            @Valid @RequestBody SimulacionPrestamoRequest request
    ) {
        return ResponseEntity.ok(
                simulacionPrestamoService.simular(
                        request.monto(),
                        request.tasaAnual(),
                        request.meses()
                )
        );
    }
}
