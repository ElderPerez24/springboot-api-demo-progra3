package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.domain.service.estado.EstadoSingletonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoEstadoController.class)
@Import(EstadoSingletonService.class)
class DemoEstadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe mantener estado en singleton")
    void debeMantenerEstadoEnSingleton() throws Exception {
        mockMvc.perform(post("/api/v1/demo/estado/singleton/reset"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorActual").value(0));

        mockMvc.perform(post("/api/v1/demo/estado/singleton/25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("singleton"))
                .andExpect(jsonPath("$.valorActual").value(25));

        mockMvc.perform(get("/api/v1/demo/estado/singleton"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorActual").value(25));
    }

    @Test
    @DisplayName("Debe reiniciar estado manual al crear instancia nueva")
    void debeReiniciarEstadoManualConInstanciaNueva() throws Exception {
        mockMvc.perform(post("/api/v1/demo/estado/manual/25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("manual"))
                .andExpect(jsonPath("$.valorActual").value(25));

        mockMvc.perform(get("/api/v1/demo/estado/manual"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("manual"))
                .andExpect(jsonPath("$.valorActual").value(0));
    }
}
