package br.com.pupposoft.poc.springbatch.legado.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.springbatch.legado.config.db.entity.CarrinhoCompraEntity;


public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompraEntity, Long> {

}
