package br.com.pupposoft.poc.springbatch.legado.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CarrinhoCompra {
	private Usuario usuario;
	private List<Item> itens;
	
	public Long getUsuarioId() {
		return usuario.getId();
	}
	
	public List<Long> obterIdsProdutos(){
		return itens.stream().mapToLong(i -> i.getProduto().getId()).boxed().toList();
	}

	public void atualizarProdutos(List<Produto> produtos) {
		produtos.forEach(p -> {
			Item item = itens.stream().filter(i -> i.getProduto().getId().equals(p.getId())).findAny().get();
			item.setProduto(p);
		});
	}
}
