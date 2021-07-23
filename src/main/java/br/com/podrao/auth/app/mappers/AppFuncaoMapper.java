package br.com.podrao.auth.app.mappers;

import org.mapstruct.Mapper;

import br.com.podrao.auth.app.models.CadastrarFuncaoRequest;
import br.com.podrao.auth.app.models.FuncaoResponse;
import br.com.podrao.auth.core.models.FuncaoDto;
import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;

@Mapper
public interface AppFuncaoMapper {

	FuncaoResponse conveter(FuncaoDto source);
	CadastrarFuncaoCommand converter(CadastrarFuncaoRequest source);
}
