package br.com.podrao.auth.core.ports.incoming;

import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;

public interface CadastrarFuncao {

	void executar(CadastrarFuncaoCommand command);

	
}
