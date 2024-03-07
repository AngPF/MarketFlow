package br.com.fiap.moviebox.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moviebox.model.Filme;

@RestController
@RequestMapping("filme")
public class FilmeController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Filme> repository = new ArrayList<>();

    @GetMapping
    public List<Filme> index() {
        return repository;
    }

    
    @GetMapping("{id}")
    public ResponseEntity<Filme> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);
        
        var optionalFilme = buscarFilmePorId(id);
        
        if (optionalFilme.isEmpty())
        return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(optionalFilme.get());
    }
    

    @PostMapping
    // @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Filme> create(@RequestBody Filme filme) {
        log.info("cadastrando filme: {}", filme);
        repository.add(filme);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(filme);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando filme {}", id);

        var optionalFilme = buscarFilmePorId(id);

        if (optionalFilme.isEmpty())
            return ResponseEntity.notFound().build();

        repository.remove(optionalFilme.get());

        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Filme filme){
        log.info("atualizando filme id {} para {}", id, filme);
        
        var optionalFilme = buscarFilmePorId(id);

        if (optionalFilme.isEmpty())
            return ResponseEntity.notFound().build();

        var filmeEncontrada = optionalFilme.get();
        var filmeAtualizada = new Filme(id, filme.nome());
        repository.remove(filmeEncontrada);
        repository.add(filmeAtualizada);

        return ResponseEntity.ok().body(filmeAtualizada);
    }

    private Optional<Filme> buscarFilmePorId(Long id) {
        var optionalFilme = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return optionalFilme;
    }

}