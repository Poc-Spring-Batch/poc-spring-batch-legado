package br.com.pupposoft.poc.springbatch.legado.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Produto {
	private Long id;
	private String nome;
	private BigDecimal preco;
	private List<Item> itens;
	
	public Integer getQuantidadeTotal() {
		return itens
		.stream()
		.mapToInt(Item::getQuantidade)
		.reduce(0, (a,b) -> a + b);
	}
	
	public List<Long> getIdCarrinhos(){
		return itens.stream().map(Item::getCarrinhoId).toList();
	}
}
