package br.com.pupposoft.poc.springbatch.legado.carrinho.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.springbatch.legado.carrinho.controller.json.CarrinhoCompraJson;
import br.com.pupposoft.poc.springbatch.legado.carrinho.usecase.AlterarCarrinhoUseCase;
import br.com.pupposoft.poc.springbatch.legado.carrinho.usecase.CriarCarrinhoUseCase;
import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;
import br.com.pupposoft.poc.springbatch.legado.domain.Item;
import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")//NOSONAR
@RequestMapping("poc/spring-batch/legado/v1")
@RestController
@AllArgsConstructor
public class CarrinhoCompraController {

	private CriarCarrinhoUseCase criarCarrinhoUseCase;
	private AlterarCarrinhoUseCase alterarCarrinhoUseCase;
	
	@PostMapping("carrinhos")
	@ResponseStatus(HttpStatus.CREATED)
	public Long criar(@RequestBody(required = true) CarrinhoCompraJson carrinhoCompraJson) {
		CarrinhoCompra carrinhoCompra = mapJsonToDomain(null, carrinhoCompraJson);
		
		return criarCarrinhoUseCase.criar(carrinhoCompra);
	}
	
	@PatchMapping("carrinhos/{carrinhoId}")
	public void alterar(
			@PathVariable("carrinhoId") Long carrinhoId, 
			@RequestBody(required = true) CarrinhoCompraJson carrinhoCompraJson) {
		CarrinhoCompra carrinhoCompra = mapJsonToDomain(carrinhoId, carrinhoCompraJson);
		
		alterarCarrinhoUseCase.alterar(carrinhoCompra);
	}
	
	private CarrinhoCompra mapJsonToDomain(Long id, CarrinhoCompraJson carrinhoCompraJson) {
		
		List<Item> itens = Arrays.asList();
		if(carrinhoCompraJson.getItens() != null) {
			itens = carrinhoCompraJson.getItens().stream()
					.map(i -> new Item(new Produto(i.getProdutoId(), null, null, null), i.getQuantidade(), null))
					.toList();
		}
		
		return new CarrinhoCompra(
				id,
				new Usuario(carrinhoCompraJson.getUsuarioId()),
				itens,
				carrinhoCompraJson.getStatus()); 
	}
}
