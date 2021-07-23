package br.com.podrao.auth.infra.mappers;

import org.mapstruct.Mapper;

import br.com.podrao.auth.core.models.FuncaoDto;
import br.com.podrao.auth.infra.entities.FuncaoEntity;

@Mapper
public interface InfraFuncaoMapper {

	FuncaoDto conveter(FuncaoEntity source);
}
