package br.com.podrao.auth.core.ports.incoming;

import br.com.podrao.auth.core.models.commands.CadastrarUsuarioCommand;

public interface CadastrarUsuario {

	void executar(CadastrarUsuarioCommand command);

}
