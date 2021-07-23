package br.com.podrao.auth.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GenericBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String mensagem;
}
