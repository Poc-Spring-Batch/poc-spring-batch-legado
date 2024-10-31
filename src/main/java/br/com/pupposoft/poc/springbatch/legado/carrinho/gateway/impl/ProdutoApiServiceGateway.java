package br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.ProdutoGateway;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.produto.controller.ProdutoController;
import br.com.pupposoft.poc.springbatch.legado.produto.controller.json.ProdutoJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoApiServiceGateway implements ProdutoGateway {

	private final ProdutoController produtoController;
	
	@Override
	public List<Produto> obterPorIds(List<Long> idsProdutos) {
			
			return idsProdutos.stream()
					.map(produtoController::obterProdutosPeloId)
					.map(this::mapJsonToDomain)
					.toList();


	}

	private Produto mapJsonToDomain(ProdutoJson produtoJson) {
		return new Produto(produtoJson.getId(), produtoJson.getNome(), BigDecimal.valueOf(produtoJson.getPreco()), null);
	}

}
