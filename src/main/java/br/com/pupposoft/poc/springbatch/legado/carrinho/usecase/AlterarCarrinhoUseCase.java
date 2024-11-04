package br.com.pupposoft.poc.springbatch.legado.carrinho.usecase;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.springbatch.legado.carrinho.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.springbatch.legado.domain.CarrinhoCompra;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlterarCarrinhoUseCase {

	private CarrinhoGateway carrinhoGateway;
	
	public void alterar(CarrinhoCompra carrinhoCompraSource) {
		
		CarrinhoCompra carrinhoCompraTarget = carrinhoGateway.obterPeloId(carrinhoCompraSource.getId());
		
		carrinhoCompraTarget.aterarStatus(carrinhoCompraSource.getStatus());
		
		carrinhoGateway.alterar(carrinhoCompraTarget);
	}
	
}
