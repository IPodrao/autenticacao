package br.com.podrao.auth.app.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class CadastrarFuncaoRequest {

	@NotBlank
	@Pattern(regexp =  "(HOLE_.*)")
	private String codigo;
	
	@NotBlank
	private String descricao;
}