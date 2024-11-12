package com.example.screenmatch_api.dto;

import java.time.LocalDate;

public record EpisodioDTO(Integer temporada,
        String titulo,
        Integer numeroEpisodio) {
}
