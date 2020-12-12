package com.generation.app.orm;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "participantes")
public class Participante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String observacoes;

    @ManyToMany(mappedBy = "participantes", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("participantes")
    private Set<Turma> turmas = new HashSet<>();

    public Set<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

    @Deprecated // Especificação que não estaremos utilizando, mas o Hibernate sim
    public Participante(){ }

    public Participante(String nome, String email, String observacoes){
        this.nome = nome;
        this.email = email;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacoes() {
        return this.observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return  "Participante{" +
                "id=" + id +
                ", nome=" + nome +
                ", email=" + email +
                ", observacoes=" + observacoes +
                "}}";
    }

}
