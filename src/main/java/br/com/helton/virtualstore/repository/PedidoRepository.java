package br.com.helton.virtualstore.repository;

import br.com.helton.virtualstore.dto.DetalhamentoPedidoDTO;
import br.com.helton.virtualstore.dto.EstatisticasVendaDTO;
import br.com.helton.virtualstore.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findPedidoByData(LocalDate now);

    @Query("""
        SELECT new br.com.helton.virtualstore.dto.DetalhamentoPedidoDTO(
            prod.nome,
            i.quantidade,
            i.precoUnitario
        )
        FROM Pedido p
        JOIN p.itens i
        JOIN i.produto prod
        WHERE p.id = :pedidoId
    """)
    List<DetalhamentoPedidoDTO> findDetalhamentoPedidoById(Long pedidoId);

    @Query("""
            SELECT SUM(i.precoUnitario * i.quantidade)
            FROM Pedido p
            JOIN p.itens i
            WHERE p.data = :data
            """)
    BigDecimal faturamentoTotalDoDia(LocalDate data);

    @Query("""
        SELECT NEW br.com.helton.virtualstore.dto.EstatisticasVendaDTO(
            prod.categoria,
            SUM(i.quantidade),
            SUM(i.precoUnitario * i.quantidade)
        )
        FROM Pedido p
        JOIN p.itens i
        JOIN i.produto prod
        WHERE p.data = :data
        GROUP BY prod.categoria
        """)
    List<EstatisticasVendaDTO> faturamentoTotalDoDiaPorCategoria(LocalDate data);

}
