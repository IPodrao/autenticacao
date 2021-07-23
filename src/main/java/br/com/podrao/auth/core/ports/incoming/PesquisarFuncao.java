package br.com.podrao.auth.core.ports.incoming;

import java.util.Collection;

import br.com.podrao.auth.core.models.FuncaoDto;

public interface PesquisarFuncao {

	Collection<FuncaoDto> executar();

}
