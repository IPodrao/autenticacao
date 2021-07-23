package br.com.podrao.auth.app.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UsuarioResponse {

	private final Long id;
	private final String nome;
	private final String sobreNome;
	private final LocalDate dataNascimento;
	private final String cpf;
	private final String email;
}
