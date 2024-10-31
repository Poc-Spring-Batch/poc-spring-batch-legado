package br.com.pupposoft.poc.springbatch.legado.produto.gateway.database;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ProdutoEntity;
import br.com.pupposoft.poc.springbatch.legado.config.db.repository.ProdutoRepository;
import br.com.pupposoft.poc.springbatch.legado.domain.Item;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;
import br.com.pupposoft.poc.springbatch.legado.exception.AcessoRepositorioDadosException;
import br.com.pupposoft.poc.springbatch.legado.produto.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ProdutoRepositoryGateway implements ProdutoGateway {

	private final ProdutoRepository produtoRepository;

	@Override
	public Optional<Produto> obterPorId(Long id) {
		try {

			return produtoRepository.findById(id)
					.map(this::mapProdutoEntityToDomain);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}
	}

	@Override
	public List<Produto> obterPorCarrinhoStatus(StatusPedido status) {
		try {

			return produtoRepository.findByItensCarrinhoStatus(status)
					.stream()
					.map(this::mapProdutoEntityToDomain)
					.toList();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}
	}

	private Produto mapProdutoEntityToDomain(ProdutoEntity produtoEntity) {
		List<Item> itens = produtoEntity.getItens().stream().map(i -> {
			ProdutoEntity produto = i.getProduto();
			return new Item(new Produto(produto.getId(), null, null, null), i.getQuantidade());
		}).toList();

		return new Produto(produtoEntity.getId(),
				produtoEntity.getNome(),
				produtoEntity.getPreco(),
				itens);
	}
}
