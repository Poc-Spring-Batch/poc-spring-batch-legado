package br.com.pupposoft.poc.springbatch.legado.carrinho.controller.json;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrinhoCompraJson {
	private Long usuarioId;
	private List<ItemCompraJson> itens;
}
