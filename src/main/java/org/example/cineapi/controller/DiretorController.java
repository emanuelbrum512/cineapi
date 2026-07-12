package org.example.cineapi.controller;

import org.example.cineapi.dto.DiretorRequest;
import org.example.cineapi.dto.DiretorResponse;
import org.example.cineapi.service.DiretorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorService service;

    public DiretorController(DiretorService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiretorResponse> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public DiretorResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public DiretorResponse salvar(@RequestBody DiretorRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public DiretorResponse atualizar(@PathVariable Long id, @RequestBody DiretorRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}