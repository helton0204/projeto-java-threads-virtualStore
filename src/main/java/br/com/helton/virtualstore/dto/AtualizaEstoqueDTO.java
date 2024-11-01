package br.com.helton.virtualstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AtualizaEstoqueDTO(
        @NotNull Long idProduto,
        @NotNull @Min(1) Integer quantidade
){}
