package com.generation.app.controllers;

import com.generation.app.repository.ParticipanteRepository;

import java.util.Optional;

import javax.validation.Valid;

import com.generation.app.orm.Participante;

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
@RequestMapping(path="/participantes") 
public class ParticipanteController {
    @Autowired
    private ParticipanteRepository repository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Participante> getAllParticipante() {
        return repository.findAll();
    }

    @RequestMapping(value = "/participante/{id}", method = RequestMethod.GET)
    public ResponseEntity<Participante> getByIdParticipante(@PathVariable(value = "id") Long id){
        Optional<Participante> participante = repository.findById(id);
        if (participante.isPresent()){
            return new ResponseEntity<Participante>(participante.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/participante/search/nome", method = RequestMethod.GET)
    public Iterable<Participante> getByNameParticipante(@RequestParam(defaultValue = "testNome") String nome){
        return repository.findByNomeContaining(nome);
    }

    @RequestMapping(value = "/participante", method = RequestMethod.POST)
    public Participante postParticipante(@Valid @RequestBody Participante participante){
        return repository.save(participante);
    }

    @RequestMapping(value = "/participante/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Participante> putParticipante(@PathVariable(value = "id") Long id, @Valid @RequestBody Participante newParticipante){
        Optional<Participante> oldParticipante = repository.findById(id);
        if (oldParticipante.isPresent()){
            Participante participante = oldParticipante.get();
            participante.setNome(newParticipante.getNome());
            participante.setEmail(newParticipante.getEmail());
            participante.setObservacoes(newParticipante.getObservacoes());
            repository.save(participante);
            return new ResponseEntity<Participante>(participante, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/participante/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Participante> deleteParticipante(@PathVariable(value = "id") Long id) {
        Optional<Participante> participante = repository.findById(id);
        if (participante.isPresent()){
            repository.delete(participante.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
