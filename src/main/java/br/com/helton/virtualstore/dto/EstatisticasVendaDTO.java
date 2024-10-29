package br.com.helton.virtualstore.dto;

import br.com.helton.virtualstore.model.Categoria;

import java.math.BigDecimal;

public record EstatisticasVendaDTO(Categoria categoria, Long quantidadesVenda, BigDecimal faturamento) {
}
