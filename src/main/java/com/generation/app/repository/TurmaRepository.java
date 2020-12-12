package com.generation.app.repository;

import com.generation.app.orm.Turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Repositorio de interações
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Iterable<Turma> findByDescricaoContaining(String descricao);
}
