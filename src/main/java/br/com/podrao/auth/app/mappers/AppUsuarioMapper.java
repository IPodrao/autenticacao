package br.com.podrao.auth.app.mappers;

import org.mapstruct.Mapper;

import br.com.podrao.auth.app.models.CadastrarUsuarioRequest;
import br.com.podrao.auth.app.models.UsuarioResponse;
import br.com.podrao.auth.core.models.UsuarioDto;
import br.com.podrao.auth.core.models.commands.CadastrarUsuarioCommand;

@Mapper
public interface AppUsuarioMapper {

	UsuarioResponse converter(UsuarioDto source);
	CadastrarUsuarioCommand converter(CadastrarUsuarioRequest source);
}
