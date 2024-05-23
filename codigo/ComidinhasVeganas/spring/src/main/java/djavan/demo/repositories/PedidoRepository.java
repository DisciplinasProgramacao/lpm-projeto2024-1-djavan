package djavan.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import djavan.demo.models.Pedido;
import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUser_Id(Long id); //retorna todos os pedidos do cliente

    List<Pedido> findByCliente_Id(Long clienteId);

    // @Query(value="SELECT * FROM a a WHERE a.cliente.id = :id", nativeQuery = true)
    // List<Pedido> findByUser_Id(@Param("id") Long id);
}
