package com.generation.app.repository;

import com.generation.app.orm.Participante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Repositorio de interações
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Iterable<Participante> findByNomeContaining(String nome);
}
