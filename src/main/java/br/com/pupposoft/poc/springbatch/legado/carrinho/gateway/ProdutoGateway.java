package br.com.pupposoft.poc.springbatch.legado.carrinho.gateway;

import java.util.List;

import br.com.pupposoft.poc.springbatch.legado.domain.Produto;

public interface ProdutoGateway {

	List<Produto> obterPorIds(List<Long> obterIdsProdutos);

}
