package br.com.helton.virtualstore.email;

import br.com.helton.virtualstore.dto.RelatorioEstoqueDTO;
import br.com.helton.virtualstore.dto.RelatorioFaturamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailRelatorioGerado {
    @Autowired
    private EnviadorEmail enviador;

    public void enviar(RelatorioEstoqueDTO estoque, RelatorioFaturamentoDTO faturamento){
        enviador.enviarEmail(
                "Relatórios gerados",
                "email de destino",
                """
                        Olá!
                        
                        Seus relatórios foram gerados e seguem abaixo!
                        
                        %s
                        
                        %s
                        """.formatted(estoque, faturamento)
        );

    }
}
