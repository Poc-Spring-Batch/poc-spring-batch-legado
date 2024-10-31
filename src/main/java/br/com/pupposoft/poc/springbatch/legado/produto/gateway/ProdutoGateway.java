package br.com.pupposoft.poc.springbatch.legado.produto.gateway;

import java.util.List;
import java.util.Optional;

import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;


public interface ProdutoGateway {

	Optional<Produto> obterPorId(Long id);

	List<Produto> obterPorCarrinhoStatus(StatusPedido status);

}
