package com.example.screenmatch_api.services.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosReposta(@JsonAlias(value = "translatedText") String textoTraduzido) {
}
