package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.api.generated.WorkshopApi;
import com.ejemplo.demo.domain.service.SaludoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaludoController implements WorkshopApi {

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @Override
    public ResponseEntity<Map<String, String>> getWorkshopHealth() {
        return ResponseEntity.ok(Map.of(
                "estado", "ok",
                "mensaje", "Workshop Spring Boot activo"
        ));
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorGet(
            @RequestParam(defaultValue = "Mundo") String nombre
    ) {
        return ResponseEntity.ok(saludoService.crearSaludo(nombre));
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorPost(@Valid @RequestBody SaludoRequest saludoRequest) {
        return ResponseEntity.ok(saludoService.crearSaludo(saludoRequest.nombre()));
    }
}
