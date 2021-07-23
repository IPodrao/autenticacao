package br.com.podrao.auth.core.models.commands;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CadastrarUsuarioCommand {

	private final String nome;
	private final String sobreNome;
	private final LocalDate dataNascimento;
	private final String cpf;
	private final String email;
	private final String senha;
}
