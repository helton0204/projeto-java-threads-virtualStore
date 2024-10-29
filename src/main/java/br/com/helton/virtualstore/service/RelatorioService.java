package br.com.helton.virtualstore.service;

import br.com.helton.virtualstore.dto.ProdutoDTO;
import br.com.helton.virtualstore.dto.RelatorioEstoqueDTO;
import br.com.helton.virtualstore.dto.RelatorioFaturamentoDTO;
import br.com.helton.virtualstore.repository.EstoqueRepository;
import br.com.helton.virtualstore.repository.PedidoRepository;
import br.com.helton.virtualstore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RelatorioService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Async
    public CompletableFuture<RelatorioEstoqueDTO> infoEstoque(){
        var produtosSemEstoque = estoqueRepository.produtosComEstoqueZerado()
                .stream().map(ProdutoDTO::new)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(new RelatorioEstoqueDTO(produtosSemEstoque));
    }

    @Async
    public CompletableFuture<RelatorioFaturamentoDTO> faturamentoObtido() {
        var dataOntem = LocalDate.now().minusDays(1);
        var faturamentoTotal = pedidoRepository.faturamentoTotalDoDia(dataOntem);

        var estatisticas = pedidoRepository.faturamentoTotalDoDiaPorCategoria(dataOntem);

        return CompletableFuture.completedFuture(new RelatorioFaturamentoDTO(faturamentoTotal, estatisticas));
    }
}