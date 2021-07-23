package br.com.podrao.auth.core.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

	private Long id;
	private String nome;
	private String sobreNome;
	private LocalDate dataNascimento;
	private String cpf;
	private String email;
	private String senha;
}
