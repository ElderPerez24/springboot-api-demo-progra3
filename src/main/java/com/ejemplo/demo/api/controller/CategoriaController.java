package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.CategoriaRequest;
import com.ejemplo.demo.api.dto.CategoriaResponse;
import com.ejemplo.demo.api.generated.CategoriasApi;
import com.ejemplo.demo.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CategoriaController implements CategoriasApi {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @Override
    public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @Override
    public ResponseEntity<CategoriaResponse> crearCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        CategoriaResponse response = categoriaService.crear(categoriaRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable Long id,
                                                                 @Valid @RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.actualizar(id, categoriaRequest));
    }

    @Override
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
