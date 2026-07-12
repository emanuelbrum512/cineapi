package org.example.cineapi.dto;

import java.time.LocalDate;
import java.util.List;

public class FilmeResponse {

    private Long id;
    private String title;
    private String description;
    private int duration;
    private LocalDate releaseDate;
    private String imageUrl;
    private Double score;
    private List<String> genres;
    private DiretorResponse diretor;

    public FilmeResponse() {
    }

    public FilmeResponse(Long id, String title, String description, int duration, LocalDate releaseDate, String imageUrl, Double score, List<String> genres, DiretorResponse diretor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.score = score;
        this.genres = genres;
        this.diretor = diretor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public DiretorResponse getDiretor() {
        return diretor;
    }

    public void setDiretor(DiretorResponse diretor) {
        this.diretor = diretor;
    }
}
