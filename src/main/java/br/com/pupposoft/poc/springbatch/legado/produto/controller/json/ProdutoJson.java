package br.com.pupposoft.poc.springbatch.legado.produto.controller.json;


import java.util.List;

import br.com.pupposoft.poc.springbatch.legado.domain.Produto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProdutoJson {
	private Long id;
	private String nome;
	private Integer quantidade;
	private Double preco;
	private List<Long> carrinhosIds;

	public ProdutoJson(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco().doubleValue();
		quantidade = produto.getQuantidadeTotal();
		carrinhosIds = produto.getIdCarrinhos();
	}
}
