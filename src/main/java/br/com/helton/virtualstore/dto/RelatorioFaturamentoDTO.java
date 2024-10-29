package br.com.helton.virtualstore.dto;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioFaturamentoDTO(BigDecimal faturamentoTotal, List<EstatisticasVendaDTO> estatisticas) {
}
