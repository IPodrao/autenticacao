package br.com.podrao.auth.infra.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Funcao")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class FuncaoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToMany(mappedBy = "funcoes")
	private Set<UsuarioEntity> usuarios;
	
	public FuncaoEntity(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
