package br.com.podrao.auth.core.ports.outgoing;

import java.util.Collection;

import br.com.podrao.auth.core.models.UsuarioDto;
import br.com.podrao.auth.core.models.commands.AtribuirFuncaoCommand;
import br.com.podrao.auth.core.models.commands.CadastrarUsuarioCommand;

public interface UsuarioPersistencia {

	void cadastrarUsuario(CadastrarUsuarioCommand command);

	void atribuirFuncao(AtribuirFuncaoCommand command);

	Collection<UsuarioDto> listarUsuarios();
}
