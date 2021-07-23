package br.com.podrao.auth.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.podrao.auth.app.mappers.AppFuncaoMapper;
import br.com.podrao.auth.app.mappers.AppUsuarioMapper;
import br.com.podrao.auth.app.models.CadastrarFuncaoRequest;
import br.com.podrao.auth.app.models.CadastrarUsuarioRequest;
import br.com.podrao.auth.app.models.FuncaoResponse;
import br.com.podrao.auth.app.models.UsuarioResponse;
import br.com.podrao.auth.core.models.commands.AtribuirFuncaoCommand;
import br.com.podrao.auth.core.models.commands.CadastrarFuncaoCommand;
import br.com.podrao.auth.core.ports.incoming.AtribuirFuncaoAoUsuario;
import br.com.podrao.auth.core.ports.incoming.CadastrarFuncao;
import br.com.podrao.auth.core.ports.incoming.CadastrarUsuario;
import br.com.podrao.auth.core.ports.incoming.PesquisaUsuario;
import br.com.podrao.auth.core.ports.incoming.PesquisarFuncao;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/autenticacao")
public class AutenticacaoController {

	private final CadastrarUsuario cadastrarUsuario;
	private final CadastrarFuncao cadastrarFuncao;
	private final PesquisaUsuario pesquisaUsuario;
	private final PesquisarFuncao pesquisarFuncao;
	private final AtribuirFuncaoAoUsuario atribuirFuncaoAoUsuario;

	private AppUsuarioMapper usuarioMapper = Mappers.getMapper(AppUsuarioMapper.class);
	private AppFuncaoMapper funcaoMapper = Mappers.getMapper(AppFuncaoMapper.class);

	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void cadastrarUsuario(@RequestBody CadastrarUsuarioRequest request) {

		cadastrarUsuario.executar(usuarioMapper.converter(request));
	}

	@GetMapping("/usuario")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Collection<UsuarioResponse> listarUsuarios() {

		return pesquisaUsuario.executar().stream().map(usuarioMapper::converter)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@PostMapping("/funcao")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void cadastrarFuncao(@RequestBody CadastrarFuncaoRequest request) {

		cadastrarFuncao.executar(new CadastrarFuncaoCommand(request.getCodigo(), request.getDescricao()));
	}

	@GetMapping("/funcao")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Collection<FuncaoResponse> listarFuncoes() {

		return pesquisarFuncao.executar().stream().map(funcaoMapper::conveter)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/funcao/{funcao}/{usuario}")
	public void atribuirFuncaoAoUsuario(@PathVariable("funcao") Long funcao, @PathVariable("usuario") Long usuario) {

		atribuirFuncaoAoUsuario.executar(new AtribuirFuncaoCommand(funcao, usuario));
	}
}
