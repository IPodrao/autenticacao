package br.com.podrao.auth.app.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FuncaoResponse {

	private final Long id;
	private final String codigo;
	private final String descricao;
}
