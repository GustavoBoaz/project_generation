package com.generation.app;

import com.generation.app.orm.Participante;
import com.generation.app.orm.Turma;
import com.generation.app.repository.ParticipanteRepository;
import com.generation.app.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	@Autowired	//Injeção de Dependencia
	ParticipanteRepository participanteRepository;
	@Autowired	//Injeção de Dependencia
	TurmaRepository turmaRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Participante participante1 = new Participante("Lucas", "lucas@email.com", "Muito dedicado.");
		Participante participante2 = new Participante("Tiago", "tiago@email.com", "Participante introvertido");
		Participante participante3 = new Participante("Marcelo", "marcelo@email.com", "Muito dedicado");
		Participante participante4 = new Participante("Luiz Lopes", "luiz@email.com", "Participante introvertido");
		participanteRepository.save(participante1);
		participanteRepository.save(participante2);
		participanteRepository.save(participante3);
		participanteRepository.save(participante4);

		Turma turma1 = new Turma("Turma Amarela", "Presencial");
		Turma turma2 = new Turma("Turma Verde", "A Distancia");
		turmaRepository.save(turma1);
		turmaRepository.save(turma2);


		turma1.getParticipantes().add(participante1);
		turma1.getParticipantes().add(participante2);
		turma2.getParticipantes().add(participante3);
		turma2.getParticipantes().add(participante4);

		turmaRepository.save(turma1);
		turmaRepository.save(turma2);

	}

}
