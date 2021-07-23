package br.com.podrao.auth.core;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.podrao.auth.core.models.FuncaoDto;
import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;
import br.com.podrao.auth.core.ports.incoming.CadastrarFuncao;
import br.com.podrao.auth.core.ports.incoming.PesquisarFuncao;
import br.com.podrao.auth.core.ports.outgoing.FuncaoPersistencia;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FuncaoFacade implements CadastrarFuncao, PesquisarFuncao {

	private final FuncaoPersistencia funcaoPersistencia;

	@Override
	public void executar(CadastrarFuncaoCommand command) {

		funcaoPersistencia.cadastrarFuncao(command);
	}

	@Override
	public Collection<FuncaoDto> executar() {
		
		return funcaoPersistencia.listarFuncoes();
	}
}
