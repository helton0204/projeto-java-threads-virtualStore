package br.com.helton.virtualstore.dto;

import java.util.List;

public record RelatorioEstoqueDTO(List<ProdutoDTO> produtosAusentes) {
}
