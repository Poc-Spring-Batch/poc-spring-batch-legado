package br.com.pupposoft.poc.springbatch.legado.carrinho.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.springbatch.legado.carrinho.controller.json.CarrinhoCompraJson;
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
	
	@PostMapping("carrinhos")
	@ResponseStatus(HttpStatus.CREATED)
	public Long criar(@RequestBody(required = true) CarrinhoCompraJson carrinhoCompraJson) {
		CarrinhoCompra carrinhoCompra = mapJsonToDomain(carrinhoCompraJson);
		
		return criarCarrinhoUseCase.criar(carrinhoCompra);
	}
	
	private CarrinhoCompra mapJsonToDomain(CarrinhoCompraJson carrinhoCompraJson) {
		List<Item> itens = carrinhoCompraJson.getItens().stream()
				.map(i -> new Item(new Produto(i.getProdutoId(), null, null), i.getQuantidade()))
				.toList();
		return new CarrinhoCompra(new Usuario(carrinhoCompraJson.getUsuarioId()), itens); 
	}
}
