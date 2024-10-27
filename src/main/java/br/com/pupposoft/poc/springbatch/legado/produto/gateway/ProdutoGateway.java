package br.com.pupposoft.poc.springbatch.legado.produto.gateway;

import java.util.Optional;

import br.com.pupposoft.poc.springbatch.legado.domain.Produto;


public interface ProdutoGateway {

	Optional<Produto> obterPorId(Long id);

}
