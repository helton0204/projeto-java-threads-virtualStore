package br.com.helton.virtualstore.email;

import br.com.helton.virtualstore.dto.DetalhamentoPedidoDTO;
import br.com.helton.virtualstore.dto.PedidoDTO;
import br.com.helton.virtualstore.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class EmailPedidoRealizado {
    @Autowired
    private EnviadorEmail enviador;

    @Async()
    public void enviar(List<DetalhamentoPedidoDTO> dto, Usuario usuario){
        // Usando StringBuilder para construir a mensagem
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá, ").append(usuario.getNome()).append("!\n\n");
        mensagem.append("Seu pedido foi registrado com sucesso. Aqui estão os detalhes dos itens:\n\n");

        final double[] totalCompra = {0.0}; // Usando um array para armazenar o total da compra

        // Itera sobre cada item do pedido e adiciona as informações na mensagem
        dto.forEach(item -> {
            BigDecimal precoUnitario = item.precoUnitario() != null ? item.precoUnitario() : BigDecimal.ZERO; // Garante que não seja null
            Integer quantidade = item.quantidade() != null ? item.quantidade() : 0; // Garante que a quantidade não seja null

            double totalItem = precoUnitario.multiply(BigDecimal.valueOf(quantidade)).doubleValue(); // Calcula o total do item como Double
            totalCompra[0] += totalItem; // Soma o total do item ao total da compra

            mensagem.append("Produto: ").append(item.nome()).append("\n");
            mensagem.append("Quantidade: ").append(quantidade).append("\n");
            mensagem.append("Preço Unitário: ").append(precoUnitario.doubleValue()).append("\n"); // Converte para Double
            mensagem.append("Total do Item: ").append(totalItem).append("\n"); // Adiciona o total do item
            mensagem.append("-------------------------------\n");
        });

        // Adiciona o total da compra à mensagem
        mensagem.append("\nTotal da Compra: ").append(totalCompra[0]).append("\n");

        // Envia o email com a mensagem formatada
        enviador.enviarEmail(
                "Pedido efetuado com sucesso na Virtual Store",
                "helton.oliveira.047@ufrn.edu.br",//usuario.getEmail(), Usa o email do usuário
                mensagem.toString()  // Corpo da mensagem formatada
        );

        System.out.println("Thread do email: " + Thread.currentThread().getName());
    }
}
