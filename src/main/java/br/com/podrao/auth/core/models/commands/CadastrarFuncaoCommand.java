package br.com.podrao.auth.core.models.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CadastrarFuncaoCommand {

	private final String codigo;
	private final String descricao;
}