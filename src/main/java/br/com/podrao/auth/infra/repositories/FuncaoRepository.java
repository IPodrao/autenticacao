package br.com.podrao.auth.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.podrao.auth.infra.entities.FuncaoEntity;

public interface FuncaoRepository extends JpaRepository<FuncaoEntity, Long> {

}
