package com.example.screenmatch_api.dto;

import com.example.screenmatch_api.model.Categoria;

public record SerieDTO(Long id, String titulo, Integer totalTemporadas, Double avaliacao,
                       Categoria genero, String atores, String poster, String sinopse) {
}
