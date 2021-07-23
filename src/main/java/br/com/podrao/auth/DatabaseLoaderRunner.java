package br.com.podrao.auth;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.podrao.auth.infra.entities.FuncaoEntity;
import br.com.podrao.auth.infra.entities.UsuarioEntity;
import br.com.podrao.auth.infra.repositories.FuncaoRepository;
import br.com.podrao.auth.infra.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseLoaderRunner implements CommandLineRunner {

	private final PasswordEncoder passwordEncoder;
	private final UsuarioRepository usuarioRepository;
	private final FuncaoRepository funcaoRepository;

	@Override
	public void run(String... args) throws Exception {

		var usuarioEntity = usuarioRepository.save(UsuarioEntity.builder().cpf("11111111111")
				.dataNascimento(Timestamp.valueOf(LocalDate.now().minusYears(20).atStartOfDay())).nome("Usuário")
				.sobreNome("ADMIN").username("admin@podrao.com").password(passwordEncoder.encode("d45s433254e45344"))
				.build());

		var funcaoEntity = funcaoRepository
				.save(new FuncaoEntity("ROLE_ADMIN", "Função de Administrador do sistema"));

		usuarioEntity.adicionarFuncao(funcaoEntity);

		usuarioRepository.save(usuarioEntity);
	}

}
