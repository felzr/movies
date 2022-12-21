package com.felzr.movies.api.enums;

public enum Messages {
    SAVE_MOVIE_SUCESS("Filme salvo com sucesso!"),
    SAVE_ALL_MOVIES("Todos os filmes forma salvos com sucesso"),
    SAVE_MOVIE_ERROR("Erro ao salvar filme!"),
    GET_ALL_MOVIES_SUCESS("Todos os filmes recuperados com sucesso!"),
    GET_MOVIE_SUCESS("Filme recuperado com sucesso!"),
    GET_MOVIE_ERROR("Erro ao recuperar filme!"),
    DELETE_MOVIE_SUCESS("Filme deletado com sucesso!"),
    EDIT_MOVIE_SUCESS("Filme editado com sucesso!");
    private String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return msg;
    }
}
