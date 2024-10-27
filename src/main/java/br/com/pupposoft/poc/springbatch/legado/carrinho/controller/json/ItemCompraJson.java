package br.com.pupposoft.poc.springbatch.legado.carrinho.controller.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemCompraJson {
	private Long produtoId;
	private Integer quantidade;
}
