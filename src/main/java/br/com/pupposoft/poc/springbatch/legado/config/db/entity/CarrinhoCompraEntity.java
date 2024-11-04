package br.com.pupposoft.poc.springbatch.legado.config.db.entity;


import java.util.List;

import br.com.pupposoft.poc.springbatch.legado.domain.StatusPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CarrinhoCompra")
public class CarrinhoCompraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idUsuario;
	
	@Setter
	private StatusPedido status;
	
	@OneToMany(mappedBy = "carrinho")
	private List<ItemEntity> itens;
}
