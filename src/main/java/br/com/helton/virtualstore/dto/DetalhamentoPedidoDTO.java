package br.com.helton.virtualstore.dto;

import java.math.BigDecimal;

public record DetalhamentoPedidoDTO(String nome, Integer quantidade, BigDecimal precoUnitario) {

    public DetalhamentoPedidoDTO(String nome, Integer quantidade, BigDecimal precoUnitario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

}

