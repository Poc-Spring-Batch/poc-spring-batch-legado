package br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.database;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.CarrinhoCompraEntity;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ItemEntity;
import br.com.pupposoft.poc.springbatch.legado.config.db.entity.ItemId;
import br.com.pupposoft.poc.springbatch.legado.config.db.repository.CarrinhoCompraRepository;
import br.com.pupposoft.poc.springbatch.legado.config.db.repository.ItemRepository;
import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;
import br.com.pupposoft.poc.springbatch.legado.domain.Item;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.domain.Usuario;
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
	public Long criar(CarrinhoCompra carrinhoCompra) {
		try {

			CarrinhoCompraEntity carrinhoEntity = 
					CarrinhoCompraEntity.builder().idUsuario(carrinhoCompra.getUsuarioId())
					.status(carrinhoCompra.getStatus())
					.build();
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

	@Override
	public CarrinhoCompra obterPeloId(Long id) {

		try {

			var carrinhoCompraEntity = carrinhoCompraRepository.findById(id).get();

			return mapToDomain(carrinhoCompraEntity);


		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}

	}

	@Override
	public void alterar(CarrinhoCompra carrinhoCompra) {
		try {
			CarrinhoCompraEntity carrinhoCompraEntity = carrinhoCompraRepository.findById(carrinhoCompra.getId()).get();
			
			//TODO: colocar demais atributos
			carrinhoCompraEntity.setStatus(carrinhoCompra.getStatus());
			
			carrinhoCompraRepository.save(carrinhoCompraEntity);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}

	}

	
	private CarrinhoCompra mapToDomain(CarrinhoCompraEntity carrinhoCompraEntity) {
		CarrinhoCompra carrinhoCompra = new CarrinhoCompra(
				carrinhoCompraEntity.getId(), 
				new Usuario(carrinhoCompraEntity.getIdUsuario()), 
				new ArrayList<>(), 
				carrinhoCompraEntity.getStatus());

		carrinhoCompraEntity.getItens().forEach(i -> {

			var produto = new Produto(
					i.getProduto().getId(),
					i.getProduto().getNome(),
					i.getProduto().getPreco(),
					null);
			
			var carrinho = new CarrinhoCompra(carrinhoCompraEntity.getId(), null, null, null);
			
			Item item = new Item(produto, i.getQuantidade(), carrinho);
			carrinhoCompra.adicionarItem(item);
		});
		return carrinhoCompra;
	}
}
