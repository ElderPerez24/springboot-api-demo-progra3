package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.exception.GlobalExceptionHandler;
import com.ejemplo.demo.domain.service.SimulacionPrestamoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SimulacionController.class)
@Import({SimulacionPrestamoService.class, GlobalExceptionHandler.class})
class SimulacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe simular prestamo correctamente")
    void debeSimularPrestamoCorrectamente() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monto": 10000,
                                  "tasaAnual": 12,
                                  "meses": 12
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuotaMensual").exists())
                .andExpect(jsonPath("$.interesTotal").exists())
                .andExpect(jsonPath("$.totalPagar").exists());
    }

    @Test
    @DisplayName("Debe fallar cuando monto es invalido")
    void debeFallarCuandoMontoEsInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monto": 0,
                                  "tasaAnual": 12,
                                  "meses": 12
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}
