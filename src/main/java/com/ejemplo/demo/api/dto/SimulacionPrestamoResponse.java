package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;

public record SimulacionPrestamoResponse(
        BigDecimal cuotaMensual,
        BigDecimal interesTotal,
        BigDecimal totalPagar
) {
}