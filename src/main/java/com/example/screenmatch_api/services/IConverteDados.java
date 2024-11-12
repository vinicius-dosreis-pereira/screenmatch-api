package com.example.screenmatch_api.services;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
