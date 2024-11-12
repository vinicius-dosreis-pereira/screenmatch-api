package com.example.screenmatch_api.controller;

import com.example.screenmatch_api.dto.EpisodioDTO;
import com.example.screenmatch_api.dto.SerieDTO;
import com.example.screenmatch_api.model.Categoria;
import com.example.screenmatch_api.model.Episodio;
import com.example.screenmatch_api.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.obterSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> listarEpisodiosDeTodasTemporadas(@PathVariable Long id) {
        return service.listarEpisodiosDeTodasTemporadas(id);
    }

    @GetMapping("{id}/temporadas/{numero}")
    public List<EpisodioDTO> listarEpisodiosDeUmaTemporada(@PathVariable Long id, @PathVariable Long numero) {
        return service.listarEpisodiosDeUmaTemporada(id, numero);
    }

    @GetMapping("categoria/{genero}")
    public List<SerieDTO> listarSeriesPorCategoria(@PathVariable String genero) {
        return service.listarSeriesPorCategoria(genero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> top5EpisodiosDaSeries(@PathVariable Long id) {
        return service.getTop5Episodes(id);
    }

}
