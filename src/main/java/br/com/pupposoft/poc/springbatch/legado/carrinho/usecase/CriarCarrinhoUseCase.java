package br.com.pupposoft.poc.springbatch.legado.carrinho.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.ProdutoGateway;
import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CriarCarrinhoUseCase {
	
	private ProdutoGateway produtoGateway;
	private CarrinhoGateway carrinhoGateway;

	public Long criar(CarrinhoCompra carrinhoCompra) {
		
		List<Produto> produtos = produtoGateway.obterPorIds(carrinhoCompra.obterIdsProdutos());
		carrinhoCompra.atualizarProdutos(produtos);
		
		return carrinhoGateway.salvar(carrinhoCompra);
	}

}
