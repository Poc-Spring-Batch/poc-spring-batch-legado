package br.com.pupposoft.poc.springbatch.legado.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.springbatch.legado.config.db.entity.EstoqueEntity;


public interface CarrinhoCompraRepository extends JpaRepository<EstoqueEntity, Long> {

}
