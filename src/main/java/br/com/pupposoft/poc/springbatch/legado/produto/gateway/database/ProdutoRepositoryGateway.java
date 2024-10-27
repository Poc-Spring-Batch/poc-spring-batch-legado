package br.com.pupposoft.poc.springbatch.legado.produto.gateway.database;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.pupposoft.poc.springbatch.legado.config.db.repository.ProdutoRepository;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
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
				.map(produtoEntity -> new Produto(
					produtoEntity.getId(),
					produtoEntity.getNome(),
					produtoEntity.getPreco()
				));
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}
	}
}
