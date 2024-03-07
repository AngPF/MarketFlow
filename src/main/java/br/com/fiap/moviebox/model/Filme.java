package br.com.fiap.moviebox.model;

import java.util.Random;

public record Filme(Long id, String nome) {

    public Filme(Long id, String nome){

        var key = (id != null) ? id : Math.abs(new Random().nextLong());
        this.id = key;
        this.nome = nome;

    }
    
}
