package org.example.cineapi.service;

import org.example.cineapi.dto.DiretorResponse;
import org.example.cineapi.dto.FilmeRequest;
import org.example.cineapi.dto.FilmeResponse;
import org.example.cineapi.exception.ResourceNotFoundException;
import org.example.cineapi.model.Diretor;
import org.example.cineapi.model.Filme;
import org.example.cineapi.repository.DiretorRepository;
import org.example.cineapi.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final DiretorRepository diretorRepository;

    public FilmeService(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
    }

    public List<FilmeResponse> listarTodos() {
        List<Filme> filmes = filmeRepository.findAll();
        List<FilmeResponse> resposta = new ArrayList<>();

        for (Filme f : filmes) {
            Diretor d = f.getDiretor();
            DiretorResponse diretorDto = new DiretorResponse(d.getId(), d.getNome(), d.getNacionalidade());

            FilmeResponse filmeDto = new FilmeResponse(
                    f.getId(),
                    f.getTitle(),
                    f.getDescription(),
                    f.getDuration(),
                    f.getReleaseDate(),
                    f.getImageUrl(),
                    f.getScore(),
                    f.getGenres(),
                    diretorDto
            );
            resposta.add(filmeDto);
        }

        return resposta;
    }

    public FilmeResponse buscarPorId(Long id) {
        Optional<Filme> optionalFilme = filmeRepository.findById(id);

        if (optionalFilme.isEmpty()) {
            throw new ResourceNotFoundException("Filme não encontrado");
        }

        Filme f = optionalFilme.get();
        Diretor d = f.getDiretor();
        DiretorResponse diretorDto = new DiretorResponse(d.getId(), d.getNome(), d.getNacionalidade());

        return new FilmeResponse(
                f.getId(),
                f.getTitle(),
                f.getDescription(),
                f.getDuration(),
                f.getReleaseDate(),
                f.getImageUrl(),
                f.getScore(),
                f.getGenres(),
                diretorDto
        );
    }

    public FilmeResponse salvar(FilmeRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do filme não pode ser nulo ou vazio.");
        }

        if (request.getScore() != null && (request.getScore() < 0.0 || request.getScore() > 10.0)) {
            throw new IllegalArgumentException("O score deve ser entre 0.0 e 10.0.");
        }

        Optional<Diretor> optionalDiretor = diretorRepository.findById(request.getDiretorId());
        if (optionalDiretor.isEmpty()) {
            throw new ResourceNotFoundException("Diretor não encontrado");
        }

        Diretor diretor = optionalDiretor.get();

        Filme filme = new Filme();
        filme.setTitle(request.getTitle().trim());
        filme.setDescription(request.getDescription());
        filme.setDuration(request.getDuration());
        filme.setReleaseDate(request.getReleaseDate());
        filme.setImageUrl(request.getImageUrl());
        filme.setScore(request.getScore());
        filme.setGenres(request.getGenres());
        filme.setDiretor(diretor);

        Filme filmeSalvo = filmeRepository.save(filme);

        DiretorResponse diretorDto = new DiretorResponse(diretor.getId(), diretor.getNome(), diretor.getNacionalidade());

        return new FilmeResponse(
                filmeSalvo.getId(),
                filmeSalvo.getTitle(),
                filmeSalvo.getDescription(),
                filmeSalvo.getDuration(),
                filmeSalvo.getReleaseDate(),
                filmeSalvo.getImageUrl(),
                filmeSalvo.getScore(),
                filmeSalvo.getGenres(),
                diretorDto
        );
    }

    public FilmeResponse atualizar(Long id, FilmeRequest request) {
        Optional<Filme> optionalFilme = filmeRepository.findById(id);
        if (optionalFilme.isEmpty()) {
            throw new ResourceNotFoundException("Filme não encontrado");
        }

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do filme não pode ser nulo ou vazio.");
        }

        if (request.getScore() != null && (request.getScore() < 0.0 || request.getScore() > 10.0)) {
            throw new IllegalArgumentException("O score deve ser entre 0.0 e 10.0.");
        }

        Optional<Diretor> optionalDiretor = diretorRepository.findById(request.getDiretorId());
        if (optionalDiretor.isEmpty()) {
            throw new ResourceNotFoundException("Diretor não encontrado");
        }

        Diretor diretor = optionalDiretor.get();
        Filme filmeExistente = optionalFilme.get();

        filmeExistente.setTitle(request.getTitle().trim());
        filmeExistente.setDescription(request.getDescription());
        filmeExistente.setDuration(request.getDuration());
        filmeExistente.setReleaseDate(request.getReleaseDate());
        filmeExistente.setImageUrl(request.getImageUrl());
        filmeExistente.setScore(request.getScore());
        filmeExistente.setGenres(request.getGenres());
        filmeExistente.setDiretor(diretor);

        Filme filmeAtualizado = filmeRepository.save(filmeExistente);

        DiretorResponse diretorDto = new DiretorResponse(diretor.getId(), diretor.getNome(), diretor.getNacionalidade());

        return new FilmeResponse(
                filmeAtualizado.getId(),
                filmeAtualizado.getTitle(),
                filmeAtualizado.getDescription(),
                filmeAtualizado.getDuration(),
                filmeAtualizado.getReleaseDate(),
                filmeAtualizado.getImageUrl(),
                filmeAtualizado.getScore(),
                filmeAtualizado.getGenres(),
                diretorDto
        );
    }

    public void excluir(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filme não encontrado");
        }
        filmeRepository.deleteById(id);
    }
}