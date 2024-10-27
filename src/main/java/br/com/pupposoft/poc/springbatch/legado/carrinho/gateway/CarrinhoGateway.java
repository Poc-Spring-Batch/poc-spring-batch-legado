package br.com.pupposoft.poc.springbatch.legado.carrinho.gateway;

import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;

public interface CarrinhoGateway {

	Long salvar(CarrinhoCompra carrinhoCompra);

}
