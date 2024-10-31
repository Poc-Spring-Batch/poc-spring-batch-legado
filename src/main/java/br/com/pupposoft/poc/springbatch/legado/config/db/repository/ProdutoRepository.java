package br.com.pupposoft.poc.springbatch.legado.config.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ProdutoEntity;
import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;


public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	List<ProdutoEntity> findByItensCarrinhoStatus(StatusPedido status);

}
