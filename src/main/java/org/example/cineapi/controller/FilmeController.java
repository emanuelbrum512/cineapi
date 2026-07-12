package org.example.cineapi.controller;

import org.example.cineapi.dto.FilmeRequest;
import org.example.cineapi.dto.FilmeResponse;
import org.example.cineapi.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public List<FilmeResponse> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public FilmeResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public FilmeResponse salvar(@RequestBody FilmeRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public FilmeResponse atualizar(@PathVariable Long id, @RequestBody FilmeRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}