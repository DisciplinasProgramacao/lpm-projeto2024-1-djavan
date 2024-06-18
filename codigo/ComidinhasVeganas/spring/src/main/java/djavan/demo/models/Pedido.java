package djavan.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidade que representa um pedido no sistema.
 */
@Entity
@Table(name = "pedido")
public class Pedido {
	private Long id;
	
  @Column(name = "valor_pedido", nullable = false)
	private Double valorPedido;
	
	private Requisicao requisicao;

	public Long getId() {
		return this.id;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

}
