package br.com.podrao.auth.core;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.podrao.auth.core.exceptions.GenericBadRequestException;
import br.com.podrao.auth.core.models.UsuarioDto;
import br.com.podrao.auth.core.models.commands.AtribuirFuncaoCommand;
import br.com.podrao.auth.core.models.commands.CadastrarUsuarioCommand;
import br.com.podrao.auth.core.ports.incoming.AtribuirFuncaoAoUsuario;
import br.com.podrao.auth.core.ports.incoming.CadastrarUsuario;
import br.com.podrao.auth.core.ports.incoming.PesquisaUsuario;
import br.com.podrao.auth.core.ports.outgoing.UsuarioPersistencia;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UsuarioFacade implements AtribuirFuncaoAoUsuario, CadastrarUsuario, PesquisaUsuario {

	private final UsuarioPersistencia usuarioPersistencia;

	@Override
	public void executar(CadastrarUsuarioCommand command) {

		if (command.getDataNascimento().minusYears(16).isAfter(LocalDate.now())) {

			throw new GenericBadRequestException("O usuário deve ter no mínimo 16 anos");
		}

		usuarioPersistencia.cadastrarUsuario(command);
	}

	@Override
	public void executar(AtribuirFuncaoCommand command) {

		usuarioPersistencia.atribuirFuncao(command);
	}

	@Override
	public Collection<UsuarioDto> executar() {

		return usuarioPersistencia.listarUsuarios();
	}
}
