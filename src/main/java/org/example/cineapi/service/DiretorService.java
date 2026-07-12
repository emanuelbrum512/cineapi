package org.example.cineapi.service;

import org.example.cineapi.dto.DiretorRequest;
import org.example.cineapi.dto.DiretorResponse;
import org.example.cineapi.exception.ResourceNotFoundException;
import org.example.cineapi.model.Diretor;
import org.example.cineapi.repository.DiretorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiretorService {

    private final DiretorRepository repository;

    public DiretorService(DiretorRepository repository) {
        this.repository = repository;
    }

    public List<DiretorResponse> listarTodos() {
        List<Diretor> diretores = repository.findAll();
        List<DiretorResponse> resposta = new ArrayList<>();

        for (Diretor d : diretores) {
            DiretorResponse dto = new DiretorResponse(d.getId(), d.getNome(), d.getNacionalidade());
            resposta.add(dto);
        }

        return resposta;
    }

    public DiretorResponse buscarPorId(Long id) {
        Optional<Diretor> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Diretor não encontrado");
        }

        Diretor d = optional.get();
        return new DiretorResponse(d.getId(), d.getNome(), d.getNacionalidade());
    }

    public DiretorResponse salvar(DiretorRequest request) {
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do diretor não pode ser nulo ou vazio.");
        }

        Diretor diretor = new Diretor();
        diretor.setNome(request.getNome().trim());
        diretor.setNacionalidade(request.getNacionalidade());

        Diretor diretorSalvo = repository.save(diretor);

        return new DiretorResponse(diretorSalvo.getId(), diretorSalvo.getNome(), diretorSalvo.getNacionalidade());
    }

    public DiretorResponse atualizar(Long id, DiretorRequest request) {
        Optional<Diretor> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Diretor não encontrado");
        }

        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do diretor não pode ser nulo ou vazio.");
        }

        Diretor diretorExistente = optional.get();
        diretorExistente.setNome(request.getNome().trim());
        diretorExistente.setNacionalidade(request.getNacionalidade());

        Diretor diretorAtualizado = repository.save(diretorExistente);

        return new DiretorResponse(diretorAtualizado.getId(), diretorAtualizado.getNome(), diretorAtualizado.getNacionalidade());
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Diretor não encontrado");
        }
        repository.deleteById(id);
    }
}