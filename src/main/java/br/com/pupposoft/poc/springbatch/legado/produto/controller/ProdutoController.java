package br.com.pupposoft.poc.springbatch.legado.produto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;
import br.com.pupposoft.poc.springbatch.legado.produto.controller.json.ProdutoJson;
import br.com.pupposoft.poc.springbatch.legado.produto.usecase.ObterProdutoUsecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")//NOSONAR
@RequestMapping("poc/spring-batch/legado/v1")
@RestController
@AllArgsConstructor
public class ProdutoController {

	private ObterProdutoUsecase obterProdutoUsecase;
	
	@GetMapping("produtos/{id}")
	public ProdutoJson obterProdutosPeloId(@PathVariable("id") Long id){
		Produto produto = obterProdutoUsecase.obterPorId(id);
		return new ProdutoJson(produto);
	}
	
	@GetMapping("carrinho-compras/status/{status}/produtos")
	public List<ProdutoJson> obterProdutosPeloStatus(@PathVariable("status") StatusPedido status){
		List<Produto> produtos = obterProdutoUsecase.obterPorCarrinhoStatus(status);
		return produtos.stream().map(ProdutoJson::new).toList();
	}
}
