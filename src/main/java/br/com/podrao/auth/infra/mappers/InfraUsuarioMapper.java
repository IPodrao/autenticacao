package br.com.podrao.auth.infra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.podrao.auth.core.models.UsuarioDto;
import br.com.podrao.auth.infra.entities.UsuarioEntity;

@Mapper
public interface InfraUsuarioMapper {

	@Mapping(target = "email", source = "source.username")
	UsuarioDto converter(UsuarioEntity source);
}
