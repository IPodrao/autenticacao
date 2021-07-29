package br.com.podrao.auth.app;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.podrao.auth.app.models.CadastrarFuncaoRequest;
import br.com.podrao.auth.app.models.CadastrarUsuarioRequest;
import br.com.podrao.auth.app.models.FuncaoResponse;
import br.com.podrao.auth.app.models.UsuarioResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Manutenção de acessos")
public interface AutenticacaoControllerOpenApi {

	@ApiOperation("Cadastrar usuário")
	public void cadastrarUsuario(@RequestBody CadastrarUsuarioRequest request);

	@ApiOperation("Lista usuários cadastraedos")
	public Collection<UsuarioResponse> listarUsuarios();

	@ApiOperation("Cadastrar função")
	public void cadastrarFuncao(@RequestBody CadastrarFuncaoRequest request);

	@ApiOperation("Listar funções cadastradas")
	public Collection<FuncaoResponse> listarFuncoes();

	@ApiOperation("Atribuir função ao usuário")
	public void atribuirFuncaoAoUsuario(@PathVariable("funcao") Long funcao, @PathVariable("usuario") Long usuario);
}
