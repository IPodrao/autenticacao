package br.com.podrao.auth.core.ports.outgoing;

import java.util.Collection;

import br.com.podrao.auth.core.models.FuncaoDto;
import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;

public interface FuncaoPersistencia {

	Collection<FuncaoDto> listarFuncoes();

	void cadastrarFuncao(CadastrarFuncaoCommand command);

}
