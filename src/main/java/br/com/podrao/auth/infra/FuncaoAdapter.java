package br.com.podrao.auth.infra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import br.com.podrao.auth.core.models.FuncaoDto;
import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;
import br.com.podrao.auth.core.ports.outgoing.FuncaoPersistencia;
import br.com.podrao.auth.infra.entities.FuncaoEntity;
import br.com.podrao.auth.infra.mappers.InfraFuncaoMapper;
import br.com.podrao.auth.infra.repositories.FuncaoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class FuncaoAdapter implements FuncaoPersistencia {

	private final FuncaoRepository funcaoRepository;

	private InfraFuncaoMapper mapper = Mappers.getMapper(InfraFuncaoMapper.class);

	@Override
	public Collection<FuncaoDto> listarFuncoes() {

		return funcaoRepository.findAll().stream().map(mapper::conveter)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public void cadastrarFuncao(CadastrarFuncaoCommand command) {

		funcaoRepository.save(new FuncaoEntity(command.getCodigo(), command.getDescricao()));
	}
}