package br.com.podrao.auth.core.ports.incoming;

import br.com.podrao.auth.core.models.commands.AtribuirFuncaoCommand;

public interface AtribuirFuncaoAoUsuario {

	void executar(AtribuirFuncaoCommand command);
}
