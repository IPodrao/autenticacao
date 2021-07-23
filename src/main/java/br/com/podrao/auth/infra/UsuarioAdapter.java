package br.com.podrao.auth.infra;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.podrao.auth.core.exceptions.GenericBadRequestException;
import br.com.podrao.auth.core.models.UsuarioDto;
import br.com.podrao.auth.core.models.commands.AtribuirFuncaoCommand;
import br.com.podrao.auth.core.models.commands.CadastrarUsuarioCommand;
import br.com.podrao.auth.core.ports.outgoing.UsuarioPersistencia;
import br.com.podrao.auth.infra.entities.FuncaoEntity;
import br.com.podrao.auth.infra.entities.UsuarioEntity;
import br.com.podrao.auth.infra.mappers.InfraUsuarioMapper;
import br.com.podrao.auth.infra.repositories.FuncaoRepository;
import br.com.podrao.auth.infra.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class UsuarioAdapter implements UsuarioPersistencia, UserDetailsService {

	private final UsuarioRepository usuarioRepository;
	private final FuncaoRepository funcaoRepository;

	private InfraUsuarioMapper mapper = Mappers.getMapper(InfraUsuarioMapper.class);

	@Override
	public void cadastrarUsuario(CadastrarUsuarioCommand command) {

		usuarioRepository.save(UsuarioEntity.builder().cpf(command.getCpf())
				.dataNascimento(Timestamp.valueOf(command.getDataNascimento().atTime(LocalTime.MIDNIGHT)))
				.nome(command.getNome()).sobreNome(command.getSobreNome()).password(command.getSenha())
				.username(command.getEmail()).build());
	}

	@Override
	public void atribuirFuncao(AtribuirFuncaoCommand command) {

		FuncaoEntity funcao = funcaoRepository.findById(command.getFuncao())
				.orElseThrow(() -> new GenericBadRequestException(
						String.format("Não existe função com o id '%s'", command.getFuncao())));

		UsuarioEntity usuario = usuarioRepository.findById(command.getUsuario())
				.orElseThrow(() -> new GenericBadRequestException(
						String.format("Não existe usuário com o id '%s'", command.getUsuario())));

		if (!usuario.adicionarFuncao(funcao)) {

			throw new GenericBadRequestException(String.format("A função '%s' já esta atribuída ao usuário '%s'",
					funcao.getCodigo(), usuario.getUsername()));
		}

		usuarioRepository.save(usuario);
	}

	@Override
	public Collection<UsuarioDto> listarUsuarios() {

		return usuarioRepository.findAll().stream().map(mapper::converter)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
