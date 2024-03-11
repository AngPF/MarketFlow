package br.com.fiap.moviebox.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.moviebox.Repository.FilmeRepository;
import br.com.fiap.moviebox.model.Filme;

@RestController
@RequestMapping("filme")
public class FilmeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired // CDI // TODO explicar
    FilmeRepository filmeRepository;

    @GetMapping
    public List<Filme> index() {
        return filmeRepository.findAll();
    }
    
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Filme create(@RequestBody Filme filme) {
        log.info("cadastrando filme: {}", filme);
        filmeRepository.save(filme);
        return filme;
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Filme> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);
       
        return filmeRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        // var filmeEncontrada = filmeRepository.findById(id);

        // if (filmeEncontrada.isEmpty())
        //     return ResponseEntity.notFound().build();
        
        // return ResponseEntity.ok(filmeEncontrada.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando filme {}", id);

        verificarSeExisteFilme(id);

        filmeRepository.deleteById(id);
        return ResponseEntity.noContent().build();

        // var optionalFilme = buscarFilmePorId(id);

        // if (optionalFilme.isEmpty())
        //     return ResponseEntity.notFound().build();

        // repository.remove(optionalFilme.get());

        // return ResponseEntity.noContent().build();

    }


    private void verificarSeExisteFilme(Long id) {
        filmeRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme  não encontrado")
            );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Filme filme){
        log.info("atualizando filme id {} para {}", id, filme);
        
        verificarSeExisteFilme(id);

        filme.setId(id);
        filmeRepository.save(filme);
        return ResponseEntity.ok(filme);

        // var optionalFilme = buscarFilmePorId(id);

        // if (optionalFilme.isEmpty())
        //     return ResponseEntity.notFound().build();

        // var filmeEncontrada = optionalFilme.get();
        // var filmeAtualizada = new Filme(id, filme.nome());
        // repository.remove(filmeEncontrada);
        // repository.add(filmeAtualizada);

        // return ResponseEntity.ok().body(filmeAtualizada);
    }

    // private Optional<Filme> buscarFilmePorId(Long id) {
    //     var optionalFilme = repository
    //             .stream()
    //             .filter(f -> f.id().equals(id))
    //             .findFirst();
    //     return optionalFilme;
    // }

}