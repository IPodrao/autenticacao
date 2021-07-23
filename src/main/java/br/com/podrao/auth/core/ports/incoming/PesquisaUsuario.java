package br.com.podrao.auth.core.ports.incoming;

import java.util.Collection;

import br.com.podrao.auth.core.models.UsuarioDto;

public interface PesquisaUsuario {

	Collection<UsuarioDto> executar();

}