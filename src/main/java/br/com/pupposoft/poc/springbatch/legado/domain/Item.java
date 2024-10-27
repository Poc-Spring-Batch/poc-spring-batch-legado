package br.com.pupposoft.poc.springbatch.legado.domain;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Item {
	@Setter(value = AccessLevel.PACKAGE)
	private Produto produto;
	private Integer quantidade;
	
	public Long getProdutoId() {
		return produto.getId();
	}
	
	public BigDecimal getProdutoPreco() {
		return produto.getPreco();
	}
}
