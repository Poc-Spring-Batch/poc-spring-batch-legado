package br.com.pupposoft.poc.springbatch.legado.carrinho.controller.json;

import java.util.List;

import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrinhoCompraJson {
	private Long id;
	private Long usuarioId;
	private List<ItemCompraJson> itens;
	private StatusPedido status;
}
