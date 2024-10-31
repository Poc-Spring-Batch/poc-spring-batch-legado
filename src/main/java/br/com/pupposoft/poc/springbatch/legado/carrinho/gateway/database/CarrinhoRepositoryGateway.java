package br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.database;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.EstoqueEntity;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ItemEntity;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ItemId;
import br.com.pupposoft.poc.springbatch.legado.config.db.repository.CarrinhoCompraRepository;
import br.com.pupposoft.poc.springbatch.legado.config.db.repository.ItemRepository;
import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;
import br.com.pupposoft.poc.springbatch.legado.exception.AcessoRepositorioDadosException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CarrinhoRepositoryGateway implements CarrinhoGateway {

	private final CarrinhoCompraRepository carrinhoCompraRepository;
	private final ItemRepository itemRepository;
	
	@Override
	@Transactional
	public Long salvar(CarrinhoCompra carrinhoCompra) {
		try {
			
			EstoqueEntity carrinhoEntity = 
					EstoqueEntity.builder().idUsuario(carrinhoCompra.getUsuarioId()).build();
			carrinhoCompraRepository.save(carrinhoEntity);
			
			final Long carrinhoId = carrinhoEntity.getId();

			carrinhoCompra.getItens().forEach(i -> {
				ItemId itemId = new ItemId(carrinhoId, i.getProdutoId());
				ItemEntity itemEntity = ItemEntity.builder()
						.id(itemId)
						.quantidade(i.getQuantidade())
						.build();
				itemRepository.save(itemEntity);
			});
			
			return carrinhoId;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}
	}
}
