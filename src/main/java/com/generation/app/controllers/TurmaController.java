package com.generation.app.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.generation.app.orm.Turma;
import com.generation.app.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Turma> getAllTurma() {
        return repository.findAll();
    }

    @RequestMapping(value = "/turma/{id}", method = RequestMethod.GET)
    public ResponseEntity<Turma> getByIdTurma(@PathVariable(value = "id") Long id){
        Optional<Turma> turma = repository.findById(id);
        if (turma.isPresent()){
            return new ResponseEntity<Turma>(turma.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/turma/search/descricao", method = RequestMethod.GET)
    public Iterable<Turma> getByNameTurma(@RequestParam(defaultValue = "testDescricao") String descricao){
        return repository.findByDescricaoContaining(descricao);
    }

    @RequestMapping(value = "/turma", method = RequestMethod.POST)
    public Turma postTurma(@Valid @RequestBody Turma turma){
        return repository.save(turma);
    }

    @RequestMapping(value = "/turma/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Turma> putTurma(@PathVariable(value = "id") Long id, @Valid @RequestBody Turma newTurma){
        Optional<Turma> oldTurma = repository.findById(id);
        if (oldTurma.isPresent()){
            Turma turma = oldTurma.get();
            turma.setTipo(newTurma.getTipo());
            turma.setDescricao(newTurma.getDescricao());
            repository.save(turma);
            return new ResponseEntity<Turma>(turma, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/turma/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Turma> deleteTurma(@PathVariable(value = "id") Long id){
        Optional<Turma> turma = repository.findById(id);
        if (turma.isPresent()){
            repository.delete(turma.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
