package com.example.screenmatch_api.services;

import com.example.screenmatch_api.dto.EpisodioDTO;
import com.example.screenmatch_api.dto.SerieDTO;
import com.example.screenmatch_api.model.Categoria;
import com.example.screenmatch_api.model.Serie;
import com.example.screenmatch_api.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterSeries() {
        return dtoConverter(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return dtoConverter(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return dtoConverter(repository.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie f = serie.get();
            return new SerieDTO(f.getId(), f.getTitulo(), f.getTotalTemporadas(), f.getAvaliacao(),
                    f.getGenero(), f.getAtores(), f.getPoster(), f.getSinopse());
        }
        return null;
    }

    private List<SerieDTO> dtoConverter(List<Serie> serieList) {
        return serieList.stream()
                .map(f -> new SerieDTO(f.getId(), f.getTitulo(), f.getTotalTemporadas(), f.getAvaliacao(),
                        f.getGenero(), f.getAtores(), f.getPoster(), f.getSinopse()))
                .collect(Collectors.toList());
    }


    public List<EpisodioDTO> listarEpisodiosDeTodasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie f = serie.get();
            return f.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> listarEpisodiosDeUmaTemporada(Long id, Long numero) {
        return repository.obterEpisodiosPorTemporada(id, numero)
                .stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> listarSeriesPorCategoria(String genero) {
        Categoria categoria = Categoria.fromPortuguese(genero);
        return dtoConverter(repository.findByGenero(categoria));
    }

    public List<EpisodioDTO> getTop5Episodes(Long id) {
        return repository.listarTop5Episodios(id)
                .stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }
}
