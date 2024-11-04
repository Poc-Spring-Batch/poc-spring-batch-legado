package br.com.pupposoft.poc.springbatch.legado.carrinho.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CarrinhoCompraControllerIntegratedTest {
	private Random random = new Random();
	
	@Test
	@Disabled//Deve ser executado com cautela, pois é um loop infinito
	void shouldSucessOnCreateCarrinhoCompra() throws Exception {
		
		while(true) {
			WebClient.Builder webClientBuilder = WebClient.builder();
			
			final WebClient webClient = webClientBuilder.build();
			
			String response = webClient.post()
					.uri("http://localhost:8080/poc/spring-batch/legado/v1/carrinhos")
					.bodyValue(getRandonRequest())
					.retrieve()
					.bodyToMono(String.class)
					.block();
			
			log.info(response);
			Thread.sleep(2000);//NOSONAR
		}
		
	}
	
	private RequestBody getRandonRequest() {
		final var userId = getRandomLong(1000L);
		final var quantidadeItens = getRandomLong(15L);
		final var itens = new ArrayList<ItemJson>();
		
		for (int i = 0; i < quantidadeItens; i++) {
			itens.add(new ItemJson(getRandomLong(3), getRandomLong(20)));
		}
		
		return new RequestBody(userId, itens);
		
	}
	
	private Long getRandomLong(long maxValue) {
        return 1 + (long) (random.nextDouble() * (maxValue - 1));
	}
	
	@Getter
	@AllArgsConstructor
	private class RequestBody {
		private Long usuarioId;
		private List<ItemJson> itens;
	}
	
	@Getter
	@AllArgsConstructor
	private class ItemJson {
		private Long produtoId;
		private Long quantidade;
	}

}
