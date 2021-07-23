package br.com.podrao.auth.app.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class CadastrarUsuarioRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobreNome;
	
	@NotNull
	private LocalDate dataNascimento;
	
	@NotBlank
	@Pattern(regexp = "(\\d{11})")
	private String cpf;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
}
