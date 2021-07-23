package br.com.podrao.auth.infra.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Usuario")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String username;

	@Column(name = "senha", nullable = false)
	private String password;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobreNome", nullable = false)
	private String sobreNome;

	@Column(name = "dataNascimento", nullable = false)
	private Timestamp dataNascimento;

	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@Getter(AccessLevel.NONE)
	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_funcao", joinColumns = {
			@JoinColumn(name = "usuario_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "funcao_id", referencedColumnName = "id") })
	private Set<FuncaoEntity> funcoes = new HashSet<>();

	@Column(name = "contaNaoExpirada", nullable = false)
	private boolean accountNonExpired = true;

	@Column(name = "contaNaoBloqueada", nullable = false)
	private boolean accountNonLocked = true;

	@Column(name = "credencialNaoExpirada", nullable = false)
	private boolean credentialsNonExpired = true;

	@Column(name = "ativo", nullable = false)
	private boolean enabled = true;

	@CreationTimestamp
	@Column(name = "dataCriacao", updatable = false)
	private Timestamp createdDate;

	@UpdateTimestamp
	@Column(name = "dataAtualizacao")
	private Timestamp lastModifiedDate;

	@Builder
	private UsuarioEntity(String username, String password, String nome, String sobreNome, Timestamp dataNascimento,
			String cpf) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return funcoes.stream().map(funcao -> new SimpleGrantedAuthority(funcao.getCodigo()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public boolean adicionarFuncao(FuncaoEntity funcao) {

		return funcoes.add(funcao);
	}
}
