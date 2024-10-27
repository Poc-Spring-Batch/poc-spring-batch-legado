package br.com.pupposoft.poc.springbatch.legado.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Produto {
	private Long id;
	private String nome;
	private BigDecimal preco;
}
