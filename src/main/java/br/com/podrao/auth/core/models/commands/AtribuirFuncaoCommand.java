package br.com.podrao.auth.core.models.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AtribuirFuncaoCommand {

	private final Long funcao;
	private final Long usuario;
}
